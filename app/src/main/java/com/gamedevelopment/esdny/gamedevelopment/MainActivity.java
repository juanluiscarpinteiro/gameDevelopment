package com.gamedevelopment.esdny.gamedevelopment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    int count=1;
    int lastTouch;
    final ImageAdapter adapter = new ImageAdapter(this);
    Handler handler = new Handler();
    boolean gameOverStatus= false;
    String backgroundPictureName;
    Random rand = new Random();
    //int alphabetLength = alphabet1.length();

    public Integer[] backgroundImages = {R.drawable.pig, R.drawable.giraffe,R.drawable.cow,R.drawable.rabbit,R.drawable.hippo,R.drawable.cat,R.drawable.dog,R.drawable.donkey,R.drawable.eagle,R.drawable.horse};

    String [] animalNames = new String []{"pig", "giraffe","cow","rabbit","hippo","cat","dog","donkey","eagle","horse"};



    public Integer[] aNumbers = {R.drawable.one,R.drawable.two,
            R.drawable.three,R.drawable.four,
            R.drawable.five,R.drawable.six,
            R.drawable.seven,R.drawable.eight,
            R.drawable.nine,R.drawable.ten,
            R.drawable.eleven,R.drawable.twelve,
            R.drawable.thirteen,R.drawable.fourteen,
            R.drawable.fifeteen,R.drawable.sixteen,
            R.drawable.seventeen,R.drawable.eighteen,
            R.drawable.nineteen,R.drawable.twenty};



    public Integer[] aNumbersNew = {R.drawable.one,R.drawable.two,
            R.drawable.three,R.drawable.four,
            R.drawable.five,R.drawable.six,
            R.drawable.seven,R.drawable.eight,
            R.drawable.nine,R.drawable.ten,
            R.drawable.eleven,R.drawable.twelve,
            R.drawable.thirteen,R.drawable.fourteen,
            R.drawable.fifeteen};


    final String [] alphabet1= new String[] {
            " A "," J "," B "," b "," C ",
            " c "," D "," d "," E "," e ",
            " F "," f "," G "," g "," a ",
            " H "," h "," I "," i "," j "
    };

    int arraySize ;
    public final Integer[] OriginalLoadingScreen = {R.drawable.one,R.drawable.two,
            R.drawable.three,R.drawable.four,
            R.drawable.five,R.drawable.six,
            R.drawable.seven,R.drawable.eight,
            R.drawable.nine,R.drawable.ten,
            R.drawable.eleven,R.drawable.twelve,
            R.drawable.thirteen,R.drawable.fourteen,
            R.drawable.fifeteen,R.drawable.sixteen,
            R.drawable.seventeen,R.drawable.eighteen,
            R.drawable.nineteen,R.drawable.twenty};

    public Integer[] emojis = {R.drawable.shy_emoji,R.drawable.fearfull_emoji,
            R.drawable.money_emoji,R.drawable.money_emoji,
            R.drawable.queen_emoji,R.drawable.queen_emoji,
            R.drawable.glass_emoji,R.drawable.glass_emoji,
            R.drawable.wink_emoji,R.drawable.wink_emoji,
            R.drawable.emoji_sexface,R.drawable.emoji_sexface,
            R.drawable.angel_emoji,R.drawable.angel_emoji,
            R.drawable.shy_emoji,R.drawable.tooth_emoji,
            R.drawable.tooth_emoji,R.drawable.alien_emoji,
            R.drawable.alien_emoji,R.drawable.fearfull_emoji};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView gr = (GridView) findViewById(R.id.gridView1);
        shuffleArray(emojis,alphabet1);
        arraySize = alphabet1.length;
        final int randomIndex = rand.nextInt(10);
        backgroundPictureName= animalNames[randomIndex];
        gr.setBackgroundResource(backgroundImages[randomIndex]);


        final Button button = (Button) findViewById(R.id.playAgainButton);
        final EditText myEditText=(EditText) findViewById(R.id.textBox);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                String UsersAnswer = (String) myEditText.getText().toString();
                UsersAnswer = UsersAnswer.replace(" ", "");

                if(UsersAnswer.equalsIgnoreCase(backgroundPictureName))
                {
                    Toast.makeText(getApplicationContext(),
                            "The Name of the Animal is " + backgroundPictureName, Toast.LENGTH_LONG).show();

                    for(int i=0;i<alphabet1.length;i++)
                    {
                        gr.getChildAt(i).setVisibility(View.INVISIBLE);
                    }
                    myEditText.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                    EndOfActivity();

                }
                else {
                    Toast.makeText(getApplicationContext(),
                            " Please Enter the name of the nimal in the picture " , Toast.LENGTH_SHORT).show();


                }

            }
        });



        gr.setAdapter (adapter);
        gr.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id)
            {
//                Toast.makeText(getBaseContext(),
//                        "pic" + (position + 1) + " selected " +"count "+ count ,
//                        Toast.LENGTH_SHORT).show();

                checkForMatch(position,gr);
               // gr.setEnabled(true);

                adapter.notifyDataSetChanged();
                count++;

            }
        });
    }

    public class ImageAdapter extends BaseAdapter {


        private Context context;

        public ImageAdapter(Context c)
        {
            context = c;
        }
        @Override
        public int getCount() {
            return aNumbers.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(aNumbers[position]);
            return imageView;
        }
    }



    public void checkForMatch(int position1,GridView gridView)
    {


        if (count % 2==1)
        {
            aNumbers[position1]=emojis[position1];
            adapter.notifyDataSetChanged();
            lastTouch=position1;

        }

        else if (count %2== 0)
        {
            gridView.setEnabled(false);
            aNumbers[position1]=emojis[position1];
            adapter.notifyDataSetChanged();


            if( alphabet1[lastTouch].equalsIgnoreCase(alphabet1[position1])&& (lastTouch!= position1||(alphabet1[lastTouch].equalsIgnoreCase("z")&&alphabet1[lastTouch].equalsIgnoreCase("z"))))
                {
//                    Toast.makeText(getApplicationContext(),
//                            " match maid "+ "this is last touch "+lastTouch + "current is " + position1 + " count "+ count , Toast.LENGTH_SHORT).show();

                    delayToDisapear(position1,gridView);

                    alphabet1[lastTouch]="z";
                    alphabet1[position1]="z";
                    adapter.notifyDataSetChanged();
                    count=0;
                    Toast.makeText(getApplicationContext(),
                    " Match Made ", Toast.LENGTH_SHORT).show();



                }
                else {

                 Toast.makeText(getApplicationContext(),
                         " match not made ", Toast.LENGTH_SHORT).show();


                    setOriginalImages(position1, gridView);


                    count =0;


                }




        }
    }
    public void setOriginalImages(int position,GridView gridView)
    {
        final GridView gr =gridView;
        final int positionLocal= position;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                aNumbers[lastTouch]=OriginalLoadingScreen[lastTouch];
                //loadscreen2[position]=" # ";
                aNumbers[positionLocal]=OriginalLoadingScreen[positionLocal];
                adapter.notifyDataSetChanged();
                gr.setEnabled(true);
            }
        }, 1000);

    }

    public void delayToDisapear(int position,GridView gridView)
    {
        final GridView gr =gridView;
        final int positionLocal= position;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gr.getChildAt(lastTouch).setVisibility(View.INVISIBLE);
                gr.getChildAt(positionLocal).setVisibility(View.INVISIBLE);
                gr.getChildAt(lastTouch).setClickable(false);
                gr.getChildAt(positionLocal).setEnabled(false);
                gr.setEnabled(true);
                adapter.notifyDataSetChanged();
                CheckForEndofGame();
            }
        }, 1000);

    }

    public void EndOfActivity()
    {

        //gridView.setVisibility(View.INVISIBLE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();

                startActivity(new Intent(MainActivity.this,winner.class ));
            }
        }, 5000);

    }

    private static void shuffleArray(Integer[] array,String[] array1)
    {
        int index, temp;
        String temp1;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            temp1 = array1[index];
            array[index] = array[i];
            array1[index] = array1[i];
            array[i] = temp;
            array1[i] = temp1;
        }
    }
    private  void CheckForEndofGame()
    {

        for (int i=0;i<arraySize;i++)
        {
            if(!(alphabet1[i].equalsIgnoreCase("z")))
            {
                break;

            }
             else{

                gameOverStatus=true;
            }

        }
        if(gameOverStatus==true)
        {
            Toast.makeText(getApplicationContext(),
                    " Please Enter the name of the nimal in the picture " , Toast.LENGTH_SHORT).show();
        }

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
