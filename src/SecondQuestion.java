import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class SecondQuestion {
     public void SecondQuestionAnswer(CompilationUnit cu) {
    	 cu.accept(new ASTVisitor() {

			@Override
			public boolean visit(MethodInvocation node) {
				  int lineNumber = cu.getLineNumber(node.getStartPosition());
				  String nodeString = node.toString();
				  String methodName=node.getName().toString();
				  String nodeIdentifier = node.getName().getIdentifier();
				  int pramSize=node.arguments().size();
				  int builtinMethodFlag=0;
				  cu.accept(new ASTVisitor() {
					@Override
					public boolean visit(MethodDeclaration node) {
						// The methods which has been called and search in the code and meet their parameter size as well
						if(methodName.equals(node.getName().toString()) && pramSize==node.parameters().size()) {
						   System.out.println("\tLine Number: "+lineNumber + " Method call: " + nodeString);
						   System.out.print("\tMethod Signature: "+nodeIdentifier+" : "+pramSize);
						   System.out.println(" : "+ node.parameters());
						}
						return true;
					}  
				  });
				return true;
			}
    		 
    	 });
     }
}
