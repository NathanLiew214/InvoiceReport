package bcWriters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriter {
	public static <T> void printJSON(String filePath, ArrayList<T> list) {

		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		try {
			PrintWriter out = new PrintWriter(new File(filePath));
			String header = null;

			if (filePath == "data/Persons.json") {
				header = "Persons";
			} else if (filePath == "data/Customers.json") {
				header = "Customers";
			} else if (filePath == "data/Products.json") {
				header = "Products";
			}

			out.write("\n");
			out.write("\"" + header + "\" :");
			out.write(gson.toJson(list));
			out.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");

		}
	}

}
