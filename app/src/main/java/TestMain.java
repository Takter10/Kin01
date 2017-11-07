import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

/**
 * Created by TnKstudio on 7/11/2560.
 */

public class TestMain {
    public static int safeLongToInt( long longNumber )
    {
        if ( longNumber < Integer.MIN_VALUE || longNumber > Integer.MAX_VALUE )
        {
            throw new IllegalArgumentException( longNumber + " cannot be cast to int without changing its value." );
        }
        return (int) longNumber;
    }
    public static void main(String[] args) {

        FirebaseDatabase.getInstance().getReference().child("Food").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int childcount = safeLongToInt(dataSnapshot.getChildrenCount());
//                selectchild = childcount.toString();
                if(childcount==0)return;
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int random = rand.nextInt(childcount);
            String selectchild = Integer.toString(random);
            System.out.println(i+" --> "+selectchild);
        }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
