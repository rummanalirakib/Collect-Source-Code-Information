import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class FirstQuestion {
     public void firstQuestionAnswer(CompilationUnit cu) {
    	 //For Showing Variable Name along with line number
    	 cu.accept(new ASTVisitor() {
			@Override
			public boolean visit(VariableDeclarationFragment node) {
 				System.out.println("\tLine No: " + (cu.getLineNumber(node.getStartPosition())) + " has variable Name: " + node.getName());
 				return true;
			}	
    	 });
     }
}
