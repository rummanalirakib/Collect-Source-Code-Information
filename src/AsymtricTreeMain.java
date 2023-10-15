import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.crypto.NodeSetData;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class AsymtricTreeMain {
	
	public void run() throws IOException {
		
		//we need to read the content of the file
		String content = FileUtils.readFileToString(new File("C:/Users/Rumman Ali/Downloads/assignment1_rumman_ali/Assignment1/src/ContentReader.java")); //replace the path of the file here
		
		//we need to create a parser object
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		
		//we need to give the content to the parser
		parser.setSource(content.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		System.out.println("First Question Result:");
		FirstQuestion firstQuestion = new FirstQuestion();
		firstQuestion.firstQuestionAnswer(cu);
		System.out.println("Second Question Result:");
		SecondQuestion secondQuestion = new SecondQuestion();
		secondQuestion.SecondQuestionAnswer(cu);
		System.out.println("Third Question Result:");
		ThirdQuestion thirdQuestion = new ThirdQuestion();
		thirdQuestion.ThirdQuestionAnswer(cu);
		System.out.println("Fourth Question Result:");
		FourthQuestion fourthQuestion = new FourthQuestion();
		fourthQuestion.FourthQuestionAnswer(cu);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AsymtricTreeMain driver = new AsymtricTreeMain();
		try {
			driver.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
