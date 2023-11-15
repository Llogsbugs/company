package telran.objects;

import java.util.List;
import java.io.*;
public class RestorePointsAppl {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		List<Point> points = null;
		try(ObjectInputStream input =
				new ObjectInputStream(new FileInputStream("points.data"))) {
			points = (List<Point>) input.readObject();
		}
		System.out.println(points);

	}

}
