package graphics;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

public class Jogl3d {
	
	private static Random r = new Random();
	private static Map<TransformationType, Float> map;
	
	private static void initMap() {
		map = new HashMap<TransformationType, Float>();
		map.put(TransformationType.ROTATE_X, 0f);
		map.put(TransformationType.ROTATE_Y, 0f);
		map.put(TransformationType.ROTATE_Z, 0f);
		map.put(TransformationType.SCALE, 1f);
		map.put(TransformationType.TRANSFORM, 0f);
		map.put(TransformationType.CUT, 0f);
		map.put(TransformationType.SPLAIN, 1f);
	}

	public static void main(String[] args) {
		GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        final GLCanvas glcanvas = new GLCanvas( glcapabilities );
        initMap();
        
        glcanvas.addGLEventListener(new GLEventListener() {

			@Override
			public void display(GLAutoDrawable drawable) {
				GL2 gl2 = drawable.getGL().getGL2();
				gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
            	gl2.glLoadIdentity();
            	gl2.glScaled(0.01, 0.01, 0.01);
            	gl2.glRotatef(map.get(TransformationType.ROTATE_X), 1f, 0f, 0f);
            	gl2.glRotatef(map.get(TransformationType.ROTATE_Y), 1f, 1f, 0f);
            	gl2.glRotatef(map.get(TransformationType.ROTATE_Z), 0f, 0f, 1f);
            	gl2.glTranslatef(map.get(TransformationType.TRANSFORM), map.get(TransformationType.TRANSFORM), 1);
            	gl2.glScalef(map.get(TransformationType.SCALE), map.get(TransformationType.SCALE), map.get(TransformationType.SCALE));
            	
            	
				drawP(gl2);
			}

			@Override
			public void dispose(GLAutoDrawable drawable) {}

			@Override
			public void init(GLAutoDrawable drawable) {
			      GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
			      
			      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
			      gl.glClearDepth(1.0f);      // set clear depth value to farthest
			      gl.glEnable(GL2.GL_DEPTH_TEST); // enables depth testing
			      gl.glDepthFunc(GL2.GL_LEQUAL);  // the type of depth test to do
			      gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
			      gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely, and smoothes out lighting
			      
			      GLU glu = new GLU(); 
	              glu.gluOrtho2D( 0.0f, 640, 0.0f, 680 );  

	              gl.glMatrixMode( GL2.GL_MODELVIEW );   
	              gl.glLoadIdentity();
	              gl.glEnable(GL2.GL_LINE_SMOOTH);
//	              glu.gluLookAt(0, 0, 0, 3, 3, 3, 10, 10, 10);

			}

			@Override
			public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
        	
        });
        final Frame frame = new Frame("KP");
        frame.add(glcanvas);
        glcanvas.addKeyListener(new KeyListener() {
			
        	@Override
        	public void keyPressed(KeyEvent e) {
        		
        		switch (e.getKeyCode()) {
				case 37:
					map.put(TransformationType.ROTATE_X, map.get(TransformationType.ROTATE_X)+1f); 
					glcanvas.display();
					break;
				case 38:
					map.put(TransformationType.ROTATE_Y, map.get(TransformationType.ROTATE_Y)+1f); 
					glcanvas.display();
					break;
				case 39:
					map.put(TransformationType.ROTATE_Z, map.get(TransformationType.ROTATE_Z)+1f); 
					glcanvas.display();
					break;
				case 40:
					map.put(TransformationType.TRANSFORM, map.get(TransformationType.TRANSFORM)+0.1f); 
					glcanvas.display();
					break;
				case 45:	
					map.put(TransformationType.SCALE, map.get(TransformationType.SCALE)+0.01f);
					glcanvas.display();
					break;
				case 61:
					map.put(TransformationType.SCALE, map.get(TransformationType.SCALE)-0.01f);
					glcanvas.display();
					break;
				default: System.out.println(e.getKeyCode()); 
				}
        	}
        	@Override
        	public void keyReleased(KeyEvent e) {}
        	
        	@Override
        	public void keyTyped(KeyEvent e) {}
		});
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                frame.remove(glcanvas);
                frame.dispose();
                System.exit( 0 );
            }
        });

        frame.setSize(840, 680);
        frame.setVisible(true);
	}
	
	private static void drawP(GL2 gl2){
		gl2.glBegin(GL2.GL_POLYGON);
		newPoint(gl2, 0, 0, 0);
		newPoint(gl2, 0, 10, 0);
		newPoint(gl2, 10, 10, 0);
		
		newPoint(gl2, 10, 0, 0);
		newPoint(gl2, 0, 0, 0);
		newPoint(gl2, 0, 0, 50);

		newPoint(gl2, 0, 10, 50);
		newPoint(gl2, 0, 10, 0);
		newPoint(gl2, 0, 10, 50);
		
		newPoint(gl2, 10, 10, 50);
		newPoint(gl2, 10, 10, 0);
		newPoint(gl2, 10, 10, 50);
		
		newPoint(gl2, 10, 0, 50);
		newPoint(gl2, 10, 0, 0);
		newPoint(gl2, 10, 0, 50);
		newPoint(gl2, 0, 0, 50);
		gl2.glEnd();
		
		gl2.glBegin(GL2.GL_POLYGON);
		newPoint(gl2, 0, 10, 50);
		newPoint(gl2, 0, 30, 50);
		newPoint(gl2, 10, 30, 50);
		
		newPoint(gl2, 10, 10, 50);
		newPoint(gl2, 10, 10, 40);
		newPoint(gl2, 10, 30, 40);
		
		newPoint(gl2, 10, 30, 50);
		newPoint(gl2, 10, 30, 40);
		newPoint(gl2, 0, 30, 40);
		
		newPoint(gl2, 0, 30, 50);
		newPoint(gl2, 0, 30, 40);
		newPoint(gl2, 0, 10, 40);
		gl2.glEnd();
		
		gl2.glBegin(GL2.GL_POLYGON);
		newPoint(gl2, 0, 30, 40);
		newPoint(gl2, 0, 20, 40);
		newPoint(gl2, 0, 20, 20);
		
		newPoint(gl2, 0, 30, 20);
		newPoint(gl2, 0, 30, 40);
		newPoint(gl2, 10, 30, 40);
		
		newPoint(gl2, 10, 30, 20);
		newPoint(gl2, 0, 30, 20);
		newPoint(gl2, 0, 30, 40);
		
		newPoint(gl2, 10, 30, 40);
		newPoint(gl2, 10, 20, 40);
		newPoint(gl2, 10, 20, 20);
		
		newPoint(gl2, 10, 30, 20);
		newPoint(gl2, 0, 30, 20);
		newPoint(gl2, 0, 20, 20);
		newPoint(gl2, 10, 20, 20);
		gl2.glEnd();
		
		gl2.glBegin(GL2.GL_POLYGON);
		newPoint(gl2, 0, 10, 30);
		newPoint(gl2, 0, 10, 20);
		newPoint(gl2, 0, 30, 20);
		
		newPoint(gl2, 0, 30, 30);
		newPoint(gl2, 0, 10, 30);
		newPoint(gl2, 10, 10, 30);
		
		newPoint(gl2, 10, 10, 20);
		newPoint(gl2, 0, 10, 20);
		newPoint(gl2, 0, 10, 30);
		
		newPoint(gl2, 10, 10, 30);
		newPoint(gl2, 10, 10, 20);
		newPoint(gl2, 10, 30, 20);
		
		newPoint(gl2, 0, 30, 20);
		newPoint(gl2, 0, 10, 20);
		newPoint(gl2, 10, 10, 20);
		
		newPoint(gl2, 10, 30, 20);
		newPoint(gl2, 10, 30, 30);
		newPoint(gl2, 0, 30, 30);
		
		newPoint(gl2, 0, 30, 20);
		newPoint(gl2, 10, 30, 20);
		gl2.glEnd();
	}
	
	private static void newPoint(GL2 gl2, int x, int y, int z) {
    	gl2.glColor3f( r.nextFloat(), r.nextFloat(), r.nextFloat());
        gl2.glVertex3f(x, y, z);
	}
}
