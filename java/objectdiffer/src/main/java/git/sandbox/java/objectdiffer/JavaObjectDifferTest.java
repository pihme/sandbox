package git.sandbox.java.objectdiffer;

import de.danielbechler.diff.ObjectDiffer;
import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.identity.IdentityStrategy;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;

public class JavaObjectDifferTest {

	public static void main(String[] args) {
		ListHolder base = new ListHolder();
		base.getItems().add(new ListItem("test", "value1"));
		
		ListHolder working = new ListHolder();
		working.getItems().add(new ListItem("test", "value2"));				
		
		ObjectDiffer defaultDiffer = ObjectDifferBuilder.buildDefault();
		
		DiffNode diff = defaultDiffer.compare(working, base);

		System.out.println("Default Differ (comment in equals method in ListItem to see it work)");
		diff.visit(new DiffNode.Visitor()
		{
		    public void node(DiffNode node, Visit visit)
		    {
		        System.out.println(node.getPath() + " => " + node.getState());
		    }
		});
				
		ObjectDiffer myDiffer = ObjectDifferBuilder.startBuilding().identity().ofCollectionItems(ListHolder.class, "items").via(new IdentityStrategy() {
			
			@Override
			public boolean equals(Object working, Object base) {
				return ((ListItem)working).getKey().equals(((ListItem)base).getKey());
			}
		}).and().build();

		System.out.println("Our Differ");
		DiffNode myDiff = myDiffer.compare(working, base);
		
		myDiff.visit(new DiffNode.Visitor()
		{
		    public void node(DiffNode node, Visit visit)
		    {
		        System.out.println(node.getPath() + " => " + node.getState());
		    }
		});
		
	}

}
