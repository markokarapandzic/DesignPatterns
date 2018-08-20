package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import mvc.DrawingModel;

public class SavePainting implements SaveStrategy {

	@Override
	public void save(Object object, File file) {

		DrawingModel model = (DrawingModel) object;
		ObjectOutputStream mObjectOutputStream = null;
		
		try {
			
			mObjectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			mObjectOutputStream.writeObject(model.getShapes());
			mObjectOutputStream.close();
			
		} catch (IOException exc) {
			exc.printStackTrace();
		}

	}

}
