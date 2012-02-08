package fractal;

import koch.Koch;
import mountain.*;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new Koch(300);
		fractals[1] = new Mountain(new Point(50, 350), new Point(250, 250), new Point(450, 400),50);
	    new FractalView(fractals, "Fraktaler");
	}

}
