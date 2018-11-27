import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Player extends Application{
	public int score1 = 0;
	public String ansCho = "0";
	public String butOne ="A";// getAnswer(1);
	public String butTwo = "B";// getAnswer(2);
	public String butThree ="C"; //getAnswer(3);
	public String butFour = "D";//getAnswer(4);
	public String question = null;
	public String[] ques;
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	String ans;
    int p=0;
    String[] r = new String[10];
    int w = 5;

	
    public Player() {
         try {
             String serverName = "10.200.95.214";
             s=new Socket(serverName,1219);
             System.out.println(s);
             din= new DataInputStream(s.getInputStream());
             dout= new DataOutputStream(s.getOutputStream());
             ClientChat();
         }
         catch(Exception e){
             System.out.println(e);
         }
     }
     @SuppressWarnings("null")
	public void ClientChat() throws IOException{
          BufferedReader read= new BufferedReader(new InputStreamReader(din));
          // BufferedWriter write = new BufferedWriter(new OutputStreamWriter(dout));
          // String receiveMessage = din.readUTF().toString();//.replace('#', '\n'); 
          // System.out.println(receiveMessage);
		   //String l = din.readUTF();
           for(int i=5;i>=0;) {
        	   r[i]=din.readUTF().replace('#', '\n');
        	   int h = r[i].indexOf('$');
        	   System.out.print(r[i]);
        	   question = r[i].substring(p+1,h);
        	   ans = r[i].substring(p, p+1);
        	   i--;
           }
          
    }
  
	
	public void start(Stage primaryStage) throws Exception {
		/*/
		 * This is the gui and the button should update when an answer is selected  
		 * was working
		 */
		
		primaryStage.setTitle("Trivia Game");
		GridPane grid = new GridPane();
		grid.setGridLinesVisible(false);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(75);
		grid.setVgap(20);
		
		Text scenetitle = new Text("Trivia!");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 30));
		grid.add(scenetitle, 0, 0, 2, 1);
		
		Label userName = new Label("Question:");
		grid.add(userName, 0, 1);
		
		Text score = new Text("Score: ");
		grid.add(score, 0, 2);
		
		Text disScore = new Text(new Integer(score1).toString());
		grid.add(disScore, 1, 2);
		
		
		Text ask = new Text(question);
		ask.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 12));
		ask.setWrappingWidth(150);
		grid.add(ask, 1,1,1, 1);
		
		
		Button queOne = new Button(butOne);
		Button queTwo = new Button(butTwo);
		Button queThree = new Button(butThree);
		Button queFour = new Button(butFour);

		queOne.setOnAction(e->{
			   ansCho="1";
			   if(ans.equals(ansCho)) {
				   score1++;
        		   System.out.println("wooo");
    			   disScore.setText(new Integer(score1).toString());
    			   ask.setText(question);
    			   w--;
        	   }

		});	
		
		grid.add(queOne, 0, 3);
		
		
		queTwo.setOnAction(e->{
			   ansCho="2";
			   if(ans.equals(ansCho)) {
				   score1++;
        		   System.out.println("wooo");
    			   disScore.setText(new Integer(score1).toString());
    			   ask.setText(question);
    			   w--;
        	   }
	          
		});		
		grid.add(queTwo, 1, 3);
		
		queThree.setOnAction(e->{
			   ansCho="3";
			   if(ans.equals(ansCho)) {
				   score1++;
        		   System.out.println("wooo");
    			   disScore.setText(new Integer(score1).toString());
    			   ask.setText(question);
    			   w--;
        	   }
	          
		});			
		grid.add(queThree, 2, 3);
	
		queFour.setOnAction(e->{
			   ansCho="4";
			   
			   if(ans.equals(ansCho)) {
				   score1++;
        		   System.out.println("wooo");
    			   disScore.setText(new Integer(score1).toString());
    			   ask.setText(question);
    			   w--;
        	   }
	           
		});			
		grid.add(queFour, 3, 3);
		
		
		try {
			if(ansCho!="0") {
				ClientChat();
			  }
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		Scene scene = new Scene(grid);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		
	}
	
	public static void main(String args[]) {
		launch(args);
		new Player();

	}	
}