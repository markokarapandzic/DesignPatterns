package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import mvc.DrawingFrame;

public class SaveLog implements SaveStrategy{

	@Override
	public void save(Object object, File file) {
		
		DrawingFrame frame = (DrawingFrame) object;
		BufferedWriter mBufferedWriter = null;
		
		try {
			
			mBufferedWriter = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			frame.getTxtArea().write(mBufferedWriter);
			
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		
	}
	
}
