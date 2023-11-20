package telran.view.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.view.*;
record Point(int x, int y) {
	
}
class InputOutputTest {
InputOutput io = new StandardInputOutput();
	@Test
	void readPointTest() {
		Point point = io.readObject("enter coordinates of point; usage <x>#<y>",
				"Wrong coordinates", str -> {
					String [] xy = str.split("#");
					if (xy.length != 2) {
						throw new RuntimeException("incorrect format of input");
					}
					int x = Integer.parseInt(xy[0]);
					int y = Integer.parseInt(xy[1]);
					return new Point(x, y);
				});
		io.writeLine(point.x() + point.y() + "");
	}

}
