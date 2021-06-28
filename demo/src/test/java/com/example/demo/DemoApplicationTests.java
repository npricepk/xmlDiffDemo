package com.example.demo;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

@SpringBootTest
class DemoApplicationTests {
	@Test
	public void given2XMLS_whenGeneratesDifferences_thenCorrect() throws IOException{
		
		FileWriter myWriter = new FileWriter("src/main/resources/diffOutput.txt");
	    ClassLoader classLoader = getClass().getClassLoader();
	    String addPath = classLoader.getResource("add.xml").getPath();
	    String subPath = classLoader.getResource("sub.xml").getPath();
		
	    Diff myDiff = DiffBuilder.compare(Input.fromFile(addPath)).withTest(Input.fromFile(subPath)).build();
	    
	    Iterator<Difference> iter = myDiff.getDifferences().iterator();
	    int size = 0;
	    while (iter.hasNext()) {
	        myWriter.write(iter.next().toString() + "\n");
	        size++;
	    }
	    myWriter.close();
	    assertThat(size, greaterThan(1));
	}

}
