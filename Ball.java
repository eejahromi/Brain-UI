package data;

import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.event.KeyEvent;


public class Ball
    {
            public static int dya = 0;
            public static int dxa = 0;
       // execute application
       public static void main( String args[] )
       {
          JFrame frame = new JFrame( "Bouncing Ball" );
          frame.setVisible(true);
          frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

          final BallPanel bp = new BallPanel();
          frame.add( bp );
          frame.setSize( 300, 300 ); // set frame size
          frame.setVisible( true ); // display frame

          //JFrame frame = new JFrame("Key Listener");

          Container contentPane = frame.getContentPane();

          KeyListener listener = new KeyListener() {

        @Override

        public void keyPressed(KeyEvent event) {
            printEventInfo("Key Pressed", event);
            if(event.getKeyCode() == 16)  
            bp.decrx();
           
            if(event.getKeyCode() == 17)
                bp.incrx();
        }

        @Override

        public void keyReleased(KeyEvent event) {

            printEventInfo("Key Released", event);
            bp.resetxy();
           
        }

        @Override

        public void keyTyped(KeyEvent event) {

            printEventInfo("Key Typed", event);
            
            if(event.getKeyChar() == 'r')  
                bp.decrx();
               
            if(event.getKeyChar() == 'l')
                bp.incrx();

        }

        private void printEventInfo(String str, KeyEvent e) {

            System.out.println(str);

            int code = e.getKeyCode();

            System.out.println("   Code: " + code);

            System.out.println("   Char: " + e.getKeyChar());

            int mods = e.getModifiersEx();

            System.out.println("    Mods: "

        + KeyEvent.getModifiersExText(mods));

            System.out.println("    Location: "

        + keyboardLocation(e.getKeyLocation()));

            System.out.println("    Action? " + e.isActionKey());

        }

        private String keyboardLocation(int keybrd) {

            switch (keybrd) {

          case KeyEvent.KEY_LOCATION_RIGHT: {
           
          }

        return "Right";

          case KeyEvent.KEY_LOCATION_LEFT: {
             
          }

        return "Left";

          case KeyEvent.KEY_LOCATION_NUMPAD:

        return "NumPad";

          case KeyEvent.KEY_LOCATION_STANDARD:

        return "Standard";

          case KeyEvent.KEY_LOCATION_UNKNOWN:

          default:

        return "Unknown";

            }

        }

          };

          JTextField textField = new JTextField();

          textField.addKeyListener(listener);

          contentPane.add(textField, BorderLayout.NORTH);

          frame.pack();

          frame.setVisible(true);

            }
         
       } // end main


    // class BallPanel

    class BallPanel extends JPanel implements ActionListener
    {
       private int delay = 10;
       protected Timer timer;

       private int x = 150;         // x position
       private int y = 150;         // y position
       private int radius = 15;     // ball radius      
       
       //controls ball speed
       public void decrx(){
               dx=1;
             
       }
       public void decry(){
               dy = dy-1;
               
       }
       public void incrx(){
               dx =-1;
               
       }
       public void incry(){
               dy = dy+1;
               
       }
       public void resetxy(){
               dy = 0;
               dx = 0;
       }
      private int dx = 0;           // increment amount (x coord)
      private int dy = 0;           // increment amount (y coord)

       public BallPanel()
       {
          timer = new Timer(delay, this);
              timer.start();                // start the timer
       }

       public void actionPerformed(ActionEvent e)
       // will run when the timer fires
       {
            repaint();
       }

       // draw rectangles and arcs
       public void paintComponent( Graphics g )
       {
          super.paintComponent( g ); // call superclass's paintComponent
            g.setColor(Color.red);

            // check for boundaries
            if (x < radius)                 dx = Math.abs(dx);
            if (x > getWidth() - radius)    dx = -Math.abs(dx);
            if (y < radius)                 dy = Math.abs(dy);
            if (y > getHeight() - radius)   dy = -Math.abs(dy);

            // adjust ball position
            x += dx;
            y += dy;
            g.fillOval(x - radius, y - radius, radius*2, radius*2);
         }
     
    }