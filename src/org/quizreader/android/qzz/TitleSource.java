package org.quizreader.android.qzz;

import java.io.IOException;
import java.net.URL;

public interface TitleSource {
	
	public URL getHTML(int section) throws IOException;

}
