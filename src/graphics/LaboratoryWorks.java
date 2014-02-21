package graphics;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

public class LaboratoryWorks {

    public static void main( String [] args ) {
        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities( glprofile );
        final GLCanvas glcanvas = new GLCanvas( glcapabilities );

        glcanvas.addGLEventListener( new GLEventListener() {
            
            @Override
            public void reshape( GLAutoDrawable glautodrawable, int x, int y, int width, int height ) {
            	LaboratoryWorks.setup( glautodrawable.getGL().getGL2(), width, height );
            }
            
            @Override
            public void init( GLAutoDrawable glautodrawable ) {
            }
            
            @Override
            public void dispose( GLAutoDrawable glautodrawable ) {
            }
            
            @Override
            public void display( GLAutoDrawable glautodrawable ) {
            	LaboratoryWorks.render( glautodrawable.getGL().getGL2(), glautodrawable.getWidth(), glautodrawable.getHeight() );
            }
        });

        final Frame frame = new Frame("KP");
        frame.add( glcanvas );
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent windowevent ) {
                frame.remove( glcanvas );
                frame.dispose();
                System.exit( 0 );
            }
        });

        frame.setSize( 840, 680 );
        frame.setVisible( true );
    }
    
    protected static void render(GL2 gl2, int width, int height ) {
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT);

        // draw a triangle filling the window
        gl2.glLoadIdentity();
        drawK(gl2, new Random());
        
        drawP(gl2, new Random());
    }
    
    protected static void drawP(GL2 gl2, Random random){
    	Random r = new Random();
    	gl2.glBegin(GL.GL_LINE_LOOP);
    	newPoint(gl2, 270, 0, r);
    	newPoint(gl2, 270, 230, r);
    	newPoint(gl2, 290, 230, r);
    	newPoint(gl2, 290, 0, r);
    	gl2.glEnd();
    	
    	gl2.glBegin(GL.GL_LINE_LOOP);
    	newPoint(gl2, 290, 230, r);
    	newPoint(gl2, 290, 210, r);
    	newPoint(gl2, 380, 210, r);
    	newPoint(gl2, 380, 230, r);
    	gl2.glEnd();
    	
    	gl2.glBegin(GL2.GL_POLYGON);
    	newPoint(gl2, 380, 230, r);
    	newPoint(gl2, 380, 120, r);
    	newPoint(gl2, 270, 120, r);
    	gl2.glEnd();
    }
    
    
    protected static void drawK(GL2 gl2, Random random){
    	Random r = new Random();
    	
    	//1
    	gl2.glBegin(GL.GL_TRIANGLES);
    	newPoint(gl2, 50, 0, r);
    	newPoint(gl2, 100, 0, r);
    	newPoint(gl2, 100, 80, r);
    	gl2.glEnd();

    	//2
    	gl2.glBegin(GL.GL_TRIANGLES);
    	newPoint(gl2, 50, 0, r);
    	newPoint(gl2, 50, 60, r);
    	newPoint(gl2, 100, 80, r);
    	gl2.glEnd();
    	
    	//3
    	gl2.glBegin(GL.GL_TRIANGLES);
    	newPoint(gl2, 50, 60, r);
    	newPoint(gl2, 100, 80, r);
    	newPoint(gl2, 90, 120, r);
    	gl2.glEnd();
    	
    	//4
    	gl2.glBegin(GL.GL_TRIANGLES);
    	newPoint(gl2, 50, 60, r);
    	newPoint(gl2, 90, 120, r);
    	newPoint(gl2, 50, 120, r);
    	gl2.glEnd();
    	
    	//5
    	gl2.glBegin(GL.GL_TRIANGLES);
    	newPoint(gl2, 90, 120, r);
    	newPoint(gl2, 50, 120, r);
    	newPoint(gl2, 70, 160, r);
    	gl2.glEnd();
    	
    	//6
    	gl2.glBegin(GL.GL_TRIANGLES);
    	newPoint(gl2, 100, 80, r);
    	newPoint(gl2, 70, 160, r);
    	newPoint(gl2, 100, 190, r);
    	gl2.glEnd();
    	
    	//7
    	gl2.glBegin(GL.GL_TRIANGLES);
    	newPoint(gl2, 50, 120, r);
    	newPoint(gl2, 70, 160, r);
    	newPoint(gl2, 50, 190, r);
    	gl2.glEnd();
    	
    	//8
    	gl2.glBegin(GL.GL_TRIANGLES);
    	newPoint(gl2, 50, 190, r);
    	newPoint(gl2, 100, 190, r);
    	newPoint(gl2, 70, 160, r);
    	gl2.glEnd();
    	
    	//9
    	gl2.glBegin(GL.GL_TRIANGLES);
    	newPoint(gl2, 50, 190, r);
    	newPoint(gl2, 100, 190, r);
    	newPoint(gl2, 100, 230, r);
    	gl2.glEnd();
    	
    	//10
    	gl2.glBegin(GL.GL_TRIANGLES);
    	newPoint(gl2, 50, 190, r);
    	newPoint(gl2, 100, 230, r);
    	newPoint(gl2, 50, 230, r);
    	gl2.glEnd();
    	
    	gl2.glBegin(GL.GL_LINE_LOOP);
    	newPoint(gl2, 100, 60, r);
    	newPoint(gl2, 200, 0, r);
    	newPoint(gl2, 250, 0, r);
    	newPoint(gl2, 110, 90, r);
    	newPoint(gl2, 250, 230, r);
    	newPoint(gl2, 220, 230, r);
    	newPoint(gl2, 100, 120, r);
    	gl2.glEnd();
    }
    
    private static void newPoint(GL2 gl2, int x, int y, Random random) {
    	gl2.glColor3f( random.nextFloat(), random.nextFloat(), random.nextFloat());
        gl2.glVertex2f( x, y );
	}

    
    protected static void setup( GL2 gl2, int width, int height ) {
        gl2.glMatrixMode(GL2.GL_PROJECTION );
        gl2.glLoadIdentity();

        GLU glu = new GLU();
        glu.gluOrtho2D( 0.0f, width, 0.0f, height );

        gl2.glMatrixMode( GL2.GL_MODELVIEW );
        gl2.glLoadIdentity();

        gl2.glViewport( 0, 0, width, height );
    }
}