/*
 This file is part of QuizReader.

 QuizReader is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 QuizReader is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with QuizReader.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.quizreader.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class WordDao extends BaseDao {

	static final String TABLE_WORD = "word";
	static final String FIELD_ID = "_id";
	static final String FIELD_LANGUAGE = "language";
	static final String FIELD_TOKEN = "token";

	public WordDao(Context context) {
		super(context);
	}

	public Word getWord(String word, String language) {
		String query = FIELD_TOKEN + " = ? AND " + FIELD_LANGUAGE + " = ?";
		Cursor cursor = db.query(TABLE_WORD, null, query, new String[] { word, language }, null, null, null);
		cursor.moveToFirst();
		if (cursor.isAfterLast()) {
			cursor.close();
			return null;
		}
		Word ret = cursorToWord(cursor);
		cursor.close();
		return ret;
	}

	public void save(Word word) {
		ContentValues cv = new ContentValues();
		cv.put(FIELD_LANGUAGE, word.getLanguage());
		cv.put(FIELD_TOKEN, word.getToken());
		long id = db.insert(TABLE_WORD, null, cv);
		word.setId(Long.toString(id));
	}

	private Word cursorToWord(Cursor cursor) {
		Word word = new Word();
		word.setId(cursor.getString(cursor.getColumnIndex(FIELD_ID)));
		word.setLanguage(cursor.getString(cursor.getColumnIndex(FIELD_LANGUAGE)));
		word.setToken(cursor.getString(cursor.getColumnIndex(FIELD_TOKEN)));
		return word;
	}

}