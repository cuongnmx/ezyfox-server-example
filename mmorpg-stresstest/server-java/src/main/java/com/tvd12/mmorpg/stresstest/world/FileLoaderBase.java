package com.tvd12.mmorpg.stresstest.world;

import com.tvd12.ezyfox.stream.EzyAnywayInputStreamLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * This class is used for configuration loader
 * 
 * @author sallyx (https://www.sallyx.org/sally/en/game-ai/)
 *
 */
public class FileLoaderBase {

	private BufferedReader __file;
	private String __line = "";
	private boolean __flagGoodFile = true;

	public FileLoaderBase(String filename) {
		try {
			__file = new BufferedReader(new InputStreamReader(new EzyAnywayInputStreamLoader().load(filename)));
		}
		catch (Exception e) {
			throw new IllegalStateException("can not load file: " + filename, e);
		}
	}

	private String __getParameterValueAsString(String line) {
		// define some delimiters
		final String delims = "[ ;=,]";
		final Pattern pattern = Pattern.compile(delims);
		var s = pattern.split(line);
		if (s.length > 0) {
			return s[s.length - 1];
		}
		return "";
	}

	private String __getNextParameter() throws IOException {
		// this will be the string that holds the next parameter
		String line;

		line = __file.readLine();

		line = removeCommentingFromLine(line);

		// if the line is of zero length, get the next line from
		// the file
		if (line.length() == 0) {
			return __getNextParameter();
		}

		line = __getParameterValueAsString(line);
		return line;
	}

	private String __getNextToken() throws IOException {
		// strip the line of any commenting
		while (__line.equals("")) {
			__line = __file.readLine();
			__line = removeCommentingFromLine(__line);
		}

		// find beginning of parameter description
		int begIdx = __line.length();
		int endIdx = __line.length();

		// define some delimiters
		final String delims = "[ ;=,]+";
		var pattern = Pattern.compile(delims);
		var matcher = pattern.matcher(__line);

		// find the end of the parameter description
		if (matcher.find()) {
			begIdx = matcher.end();
			if (matcher.find()) {
				endIdx = matcher.start();
			} else {
				endIdx = __line.length();
			}
		}

		String s = __line.substring(begIdx, endIdx);

		if (endIdx != __line.length()) {
			// strip the token from the line
			__line = __line.substring(endIdx + 1, __line.length());
		} else {
			__line = "";
		}

		return s;

	}

	// helper methods. They convert the next parameter value found into the
	// relevant type
	public double getNextParameterDouble() throws IOException {
		if (__flagGoodFile) {
			return Double.valueOf(__getNextParameter());
		}
		throw new RuntimeException("bad file");
	}

	public float getNextParameterFloat() throws IOException {
		if (__flagGoodFile) {
			return Float.valueOf(__getNextParameter());
		}
		throw new RuntimeException("bad file");
	}

	public int getNextParameterInt() throws IOException {
		if (__flagGoodFile) {
			return Integer.valueOf(__getNextParameter());
		}
		throw new RuntimeException("bad file");
	}

	public boolean getNextParameterBool() throws IOException {
		if (__flagGoodFile) {
			return 0 != Integer.valueOf(__getNextParameter());
		}
		throw new RuntimeException("bad file");
	}

	public double getNextTokenAsDouble() throws IOException {
		if (__flagGoodFile) {
			return Double.valueOf(__getNextToken());
		}
		throw new RuntimeException("bad file");
	}

	public float getNextTokenAsFloat() throws IOException {
		if (__flagGoodFile) {
			return Float.valueOf(__getNextToken());
		}
		throw new RuntimeException("bad file");
	}

	public int getNextTokenAsInt() throws IOException {
		if (__flagGoodFile) {
			return Integer.valueOf(__getNextToken());
		}
		throw new RuntimeException("bad file");
	}

	public String getNextTokenAsString() throws IOException {
		if (__flagGoodFile) {
			return __getNextToken();
		}
		throw new RuntimeException("bad file");
	}

	public boolean isEOF() throws IOException {
		if (__flagGoodFile) {
			return !__file.ready();
		}
		throw new RuntimeException("bad file");
	}

	public boolean isFileIsGood() {
		return __flagGoodFile;
	}

	// removes any commenting from a line of text
	public static String removeCommentingFromLine(String line) {
		// search for any comment and remove
		int idx = line.indexOf("//");

		if (idx != -1) {
			// cut out the comment
			return line.substring(0, idx);
		}
		return line;
	}

}
