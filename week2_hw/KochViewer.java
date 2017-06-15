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

	private void drawKoch (int level, double x1, double y1, double x2, double y2){
			double sqrt3 = Math.sqrt(3.0);
		if (level==0) {drawLine (x1, y1, x2, y2);}
		else{
			double x3 = (1.0/2.0)*((x1+x2)+(1.0/sqrt3)*(y1-y2));
			double y3 = (1.0/2.0)*((y1+y2)-(1.0/sqrt3)*(x1-x2));
			drawKoch (level-1, x1, y1, (2*x1+x2)/3, (2*y1+y2)/3);
			drawKoch (level-1, (2*x1+x2)/3, (2*y1+y2)/3, x3, y3);
			drawKoch (level-1, x3, y3, (x1+2*x2)/3, (y1+2*y2)/3);
			drawKoch (level-1, (x1+2*x2)/3, (y1+2*y2)/3, x2, y2);
		}
	}
	
	private void drawLine (double x1, double y1, double x2, double y2){
		int sx = (int)((x1 - xmin)*ratio);
		int sy = (int)((y1 - ymin)*ratio);
		double exf = ((x2 - xmin)*ratio);
		int ex = (int)((x2 - xmin)*ratio);
		int ey = (int)((y2 - ymin)*ratio);
		Line line = new Line( sx,height-sy-1,ex,height-ey-1 );
		palette.getChildren().add(line);
	}

	private void repaint(){
		palette.getChildren().setAll();
		drawKoch(depth, 0.0, 0.0, 1.0, 0.0);
	}

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
		button.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent event) {
				depth = Integer.parseInt(value.getText()); 
				repaint(); 
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