import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.GridPane;
import java.util.Iterator;
import javafx.scene.control.ScrollBar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;


public class PolygonViewer extends Application {
	private final int w = 800;
	private final int h = 800;
	private final String fileName = "dragon.txt";

	private Group root;
	private Group palette;
	private GridPane pane;
	private ScrollBar scrX,scrY,scrZ,scrZoom;

	private Shape3d shape;

	@Override
	public void init(){
		shape = new Shape3d(fileName);
	}

	// 多角形の頂点が ( x[i], y[i], z[i] ) であるときに、
	// その多角形を表示する。xp, yp は画面中心の値。
	public void drawPolygon (double[]x, double[]y, double[] z){
		int xp = 400;
		int yp = 400;
		Color co = Color.BLACK;
		double x1, y1, z0, x2, y2;
		int len = x.length;
		double[] xx = new double[len];
		double[] yy = new double[len];
		double[] xw = new double[len];
		double[] yw = new double[len];	
		final double RAD = Math.PI / 180.0;
		
		double a = scrX.getValue() * RAD;
		double b = scrY.getValue() * RAD;
		double c = scrZ.getValue() * RAD;
		int zoom = (int)scrZoom.getValue();

		// 座標を回転した後に、２次元の画面上に投影する。
		double sinA = Math.sin(a), cosA = Math.cos(a);
		double sinB = Math.sin(b), cosB = Math.cos(b);
		double sinC = Math.sin(c), cosC = Math.cos(c);	
		
		for(int i=0;i<len; i++) {
			x1 = x[i]*cosC - y[i]*sinC;
			y1 = x[i]*sinC + y[i]*cosC;
			x2 =  x1*cosB + z[i]*sinB;
			z0 = -x1*sinB + z[i]*cosB;
			y2 =  y1*cosA - z0*sinA;
			xx[i] = xp + (int)Math.rint(x2)*zoom;
			yy[i] = yp - (int)Math.rint(y2)*zoom;
			xw[i] = x2; yw[i] = y2;
		}
		if(Hvec(xw,yw)>0) { // 面の法線のZ値が正のときのみ表示
			Polygon polygon = new Polygon(new double[]{
				(double)xx[0], (double)yy[0],
				(double)xx[1], (double)yy[1],
				(double)xx[2], (double)yy[2],
			});
			palette.getChildren().add(polygon);
		}
	}

	// 外積のZ値を返す。
	private double Hvec(double[] x, double[] y) {
		return x[0]*(y[1]-y[2])+x[1]*(y[2]-y[0])+x[2]*(y[0]-y[1]);
	}

	private void paint(){
		
		/*
		 * drawPolygon()を呼んで、init()で取得した変数[shape]を表示するプログラムを書く。
		 * 各 face に対して、double[] x, y, z に座標を代入し、drawPolygon(x, y, z)を呼ぶこと。
		 */

		//
		// *** ここからプログラムを追加する
		//
        int i = 0;
        Iterator iter = shape.faceList.iterator();
        while(iter.hasNext()){
            //System.out.printf("painting face #"); System.out.println(i);i++;
            Face face = (Face)iter.next();
            paintFace(face);
        }
            
        /*
        int n_face = shape.faceList.size();
        
        for(int i = 0; i < n_face; i++){
            
            System.out.printf("painting face #"); System.out.println(i);
            paintFace( shape.faceList.get(i) );
       
        }
         */
        /*
        double[] x = new double[3];
        double[] y = new double[3];
        double[] z = new double[3];
        x[0]=100;x[1]=0;x[2]=0;
        y[0]=0;y[1]=100;y[2]=0;
        z[0]=0;z[1]=0;z[2]=100;
        drawPolygon(x,y,z);
         */
	}
    
    private void paintFace(Face face){
        int n_vertex = face.vertices.length;
        
        for(int j=0;j<n_vertex-2;j++){
            
            double[] x = new double[3];
            double[] y = new double[3];
            double[] z = new double[3];
            
            Vertex vert;
            vert = face.vertices[0];
            x[0] = vert.x;
            y[0] = vert.y;
            z[0] = vert.z;
            vert = face.vertices[j+1];
            x[1] = vert.x;
            y[1] = vert.y;
            z[1] = vert.z;
            vert = face.vertices[j+2];
            x[2] = vert.x;
            y[2] = vert.y;
            z[2] = vert.z;
            
            drawPolygon(x,y,z);
        }
        /*
         
        double[] x = new double[n_vertex];
        double[] y = new double[n_vertex];
        double[] z = new double[n_vertex];
         
        for (int j = 0; j < n_vertex; j++ ){
            Vertex vert = face.vertices[j];
            x[j] = vert.x;
            y[j] = vert.y;
            z[j] = vert.z;
            
            System.out.printf("point #");System.out.println(j);
            
            System.out.printf("x:");System.out.println(x[j]);
            System.out.printf("y:");System.out.println(y[j]);
            System.out.printf("z:");System.out.println(z[j]);
        }
        System.out.println();

        drawPolygon(x, y, z);
        */
    }
    
	//再描画をする。
	private void repaint(){
		palette.getChildren().setAll();//paletteに登録してあるものをリセット
		paint();//再描画
	}

	@Override
	public void start(final Stage primaryStage){
		root = new Group();
		primaryStage.setScene(new Scene(root, w, h));

		palette = new Group();
		root.getChildren().add(palette);

		pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(3);

		Label xLabel = new Label(" X-Rotation (-90 .. +90) ");
		pane.add(xLabel,0,0);

		scrX = new ScrollBar();
		scrX.setMin(-90);
		scrX.setMax(90);
		scrX.setValue(0);
		scrX.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				repaint();
			}
		});
		pane.add(scrX,1,0);

		Label yLabel = new Label(" Y-Rotation (-90 .. +90) ");
		pane.add(yLabel,0,1);

		scrY = new ScrollBar();
		scrY.setMin(-90);
		scrY.setMax(90);
		scrY.setValue(0);
		scrY.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				repaint();
			}
		});
		pane.add(scrY,1,1);


		Label zLabel = new Label(" Z-Rotation (-90 .. +90) ");
		pane.add(zLabel,0,2);

		scrZ = new ScrollBar();
		scrZ.setMin(-90);
		scrZ.setMax(90);
		scrZ.setValue(0);
		scrZ.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				repaint();
			}
		});
		pane.add(scrZ,1,2);

		Label zoomLabel = new Label(" Zoom (x1 .. x5) ");
		pane.add(zoomLabel,0,3);

		scrZoom = new ScrollBar();
		scrZoom.setMin(1);
		scrZoom.setMax(5);
		scrZoom.setValue(1);
		scrZoom.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				repaint();
			}
		});
		pane.add(scrZoom,1,3);

		root.getChildren().add(pane);

		paint();

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
