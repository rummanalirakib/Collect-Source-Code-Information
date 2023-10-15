import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class FourthQuestion {
	public void FourthQuestionAnswer(CompilationUnit cu) {
	   	 cu.accept(new ASTVisitor() {

			@Override
			public boolean visit(MethodDeclaration node) {
				String methodDecription="";
				//This section has been done for getting the full method description
				for(int i=0;i<node.toString().length();i++) {
					methodDecription+=node.toString().charAt(i);
					if(node.toString().charAt(i)==')') break;
				}
				ArrayList<String> VariableName = new ArrayList<String>();
				ArrayList<ArrayList<String>> twoDList = new ArrayList<>();
				ArrayList<ArrayList<Integer>> flagVar = new ArrayList<>();
				ArrayList<ArrayList<String>> methodInvocName = new ArrayList<>();
				ArrayList<ArrayList<Integer>> methodInvocLineNumber = new ArrayList<>();
				for(int i=0;i<1000;i++) {
					twoDList.add(new ArrayList<String>());
					flagVar.add(new ArrayList<Integer>());
					methodInvocName.add(new ArrayList<String>());
					methodInvocLineNumber.add(new ArrayList<>());
				}
				node.getBody().accept(new ASTVisitor() { // this is only visiting that specific method body

					@Override
					public boolean visit(VariableDeclarationFragment node1) {
						VariableName.add(node1.getName().toString());              
						return true;
					}

					@Override
					public boolean visit(MethodInvocation node1) {
						// This section gets the method call which has been called from a method argument and keeps their method call depending on the variable in the array list
						for(int i=0; i<VariableName.size();i++)
						{
							String temp=VariableName.get(i);
							if(node1.arguments().toString().contains(temp + ".")) {
								flagVar.get(i).add(1); // It is tracking whether method is invoked with variable name in arguments. 2D variables have been used to keep track depending on the variable index
							}
							
							if(node1.arguments().toString().contains(temp)) {
								twoDList.get(i).add(node1.getName().toString()); // It is saving the Method Invoke name
								methodInvocName.get(i).add(node1.arguments().toString()); // It is saving the method Invoked arguments name
								methodInvocLineNumber.get(i).add(cu.getLineNumber(node1.getStartPosition())); // It is saving the line number
							}
						}
						return true;
					}
					
					
				});
				// This section is the final period for showing all the details for the third question to be shown
				for(int i=0;i<twoDList.size();i++)
				{
					if(twoDList.get(i).size()>0 && flagVar.get(i).size()>0) {
						int index = twoDList.get(i).size();
						System.out.println("\tLine Number: " + cu.getLineNumber(node.getStartPosition())+ " Method Declaration: " + methodDecription);
						System.out.println("\t\tLine: " + methodInvocLineNumber.get(i).get(index-1) + " Method Call " + methodInvocName.get(i).get(index-1));
					    System.out.println("\t\tMethod that use \""+ VariableName.get(i) + "\" in their arguments: " + twoDList.get(i));
					}
				}
				 return true;
	   	       }
			});
	}
}
