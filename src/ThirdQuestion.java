import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class ThirdQuestion {
	public void ThirdQuestionAnswer(CompilationUnit cu) {
		cu.accept(new ASTVisitor() {

			@Override
			public boolean visit(MethodDeclaration node) {
				String methodDecription = "";
				// This section has been done for getting the full method description
				for (int i = 0; i < node.toString().length(); i++) {
					methodDecription += node.toString().charAt(i);
					if (node.toString().charAt(i) == ')')
						break;
				}
				//This arraylist has been declared for keeping the varaible name and method descriptions in the array and which I am going to use later
				ArrayList<String> VariableName = new ArrayList<String>();
				ArrayList<String> VariableInitializer = new ArrayList<String>();
				//2D arrays have been called so that for each specific variable if there any invoked methods exist then depending on that variable's index value
				//it is going to save those invoke methods on that index value
				ArrayList<ArrayList<Integer>> methodInvocLineNumber = new ArrayList<>();
				ArrayList<ArrayList<String>> twoDList = new ArrayList<>();
				for (int i = 0; i < 1000; i++) {
					twoDList.add(new ArrayList<String>());
					methodInvocLineNumber.add(new ArrayList<Integer>());
				}
				node.getBody().accept(new ASTVisitor() {

					@Override
					public boolean visit(VariableDeclarationFragment node1) {
						VariableName.add(node1.getName().toString());
						if(node1.getInitializer()!=null) VariableInitializer.add(node1.getInitializer().toString()); // This is getting the variable initializer.
						else VariableInitializer.add("null");
						return true;
					}

					@Override
					public boolean visit(MethodInvocation node1) {
						// This section gets the method call which has been called from a method and
						// keeps their method call depending on the variable in the arraylist
						for (int i = 0; i < VariableName.size(); i++) {
							String temp = VariableName.get(i);
							if (node1.getExpression() != null && temp.equals(node1.getExpression().toString())) {
								twoDList.get(i).add(node1.toString()); // It is saving the Method Invoke name
								methodInvocLineNumber.get(i).add(cu.getLineNumber(node1.getStartPosition())); // It is saving the line number of the invoked Method
							}
						}
						return true;
					}

				});
				// This section is the final period for showing all the details for the third question to be shown
				for (int i = 0; i < twoDList.size(); i++) {
					if (twoDList.get(i).size() >= 1) {
						int index = twoDList.get(i).size();
						System.out.println("\tLine Number: " + cu.getLineNumber(node.getStartPosition())
								+ " Method Declaration: " + methodDecription);
						System.out.println("\t\tLine: " + methodInvocLineNumber.get(i).get(index - 1) + " Method Call: "
								+ twoDList.get(i).get(index - 1));
						if (twoDList.get(i).size() > 1) { //This condition is for if there are methods which have been invoked with that variable if invoked then show them otherwise print the else block
							System.out.print("\t\tMethod Call On \"" + VariableName.get(i) + "\":  "+GetVariableInitializer(VariableInitializer.get(i))+" ");
							for (int j = 0; j < twoDList.get(i).size() - 1; j++) {
								System.out.print(twoDList.get(i).get(j) + "  ");
							}
						} else {
							System.out.print("\t\tMethod Call On \"" + VariableName.get(i)
									+ "\":  No method call has been done prior to this one.");
						}
						System.out.println();
					}
				}
				return true;
			}
		});
	}
	
	// This portion has been done for getting the variable initializer
	public String GetVariableInitializer(String variableInitializer) {
		String initializer="";
		int flag=0;
		int size=variableInitializer.length();
		for(int i=0;i<size-3;i++) {
			if(variableInitializer.charAt(i)=='n' && variableInitializer.charAt(i+1)=='e' && variableInitializer.charAt(i+2)=='w' && variableInitializer.charAt(i+3)==' ') {
				flag=1;
				size+=3;
				i+=3;
			}
			else if(flag==1) {
				if(variableInitializer.charAt(i)=='(') {
					initializer+="()";
					break;
				}
				initializer+=variableInitializer.charAt(i);
			}
		}
		return initializer;
	}
}
