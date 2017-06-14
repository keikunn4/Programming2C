import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class KochViewer extends Application{

	private final int w = 800;
	private final int h = 400;

	private Group root;
	private Group palette;

	private double xmax, xmin, ymax, ymin, ratio;
	private int width, height;
	private Label title;
	private TextField value;
	private Button button;
	private int depth;

	// コッホ曲線を描く再帰関数。level は、コッホ曲線のレベル。
	private void drawKoch (int level, double x1, double y1, double x2, double y2){
		//［ (１)ここに再帰関数を書く (たぶん２０行以下程度のプログラム) ］
		// 画面上に線分を描く関数は、drawLine (double x1, double y1,double x2, double y2)
		// を用いること。

		//
		// *** ここからプログラムを追加する
		//
		//
	 	// *** 追加はここまで
	 	//
	}

	//線分を画面に描画する。
	private void drawLine (double x1, double y1, double x2, double y2){
		int sx = (int)((x1 - xmin)*ratio);
		int sy = (int)((y1 - ymin)*ratio);
		double exf = ((x2 - xmin)*ratio);
		int ex = (int)((x2 - xmin)*ratio);
		int ey = (int)((y2 - ymin)*ratio);
		Line line = new Line( sx,height-sy-1,ex,height-ey-1 );
		palette.getChildren().add(line);
	}

	//再描画をする。
	private void repaint(){
		palette.getChildren().setAll();//paletteに登録してあるものをリセット(消す)
		drawKoch(depth, 0.0, 0.0, 1.0, 0.0);//再度描画する
	}

	// アプレット起動時に一度だけ呼ばれる関数
	@Override
	public void init(){
		depth = 1;
		xmin = ymin = -0.1;
		xmax = ymax = 1.1;
		width  = w;
		height = h;
		ratio = width / (xmax - xmin);
	}

	@Override
	public void start(final Stage primaryStage) {
		root = new Group();
		primaryStage.setScene(new Scene(root, w, h));

		FlowPane pane = new FlowPane();

		title = new Label("Level   ");
		value = new TextField("1");
		value.setPrefWidth(30);
		button = new Button("Button");
		button.setOnAction(new EventHandler<ActionEvent>() { //ボタンが押された時に再描画する．
			public void handle(ActionEvent event) {
				depth = Integer.parseInt(value.getText()); //入力された数値を格納
				repaint(); // 再描画する．
			}
		});

		pane.getChildren().add(title);
		pane.getChildren().add(value);
		pane.getChildren().add(button);
		root.getChildren().add(pane);

		palette = new Group();
		drawKoch(depth, 0.0, 0.0, 1.0, 0.0);
		root.getChildren().add(palette);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}