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

public class LaboratoryWorks {
	private static Map<TransformationType, Float> map;
	
	private static void initMap() {
		map = new HashMap<TransformationType, Float>();
		map.put(TransformationType.ROTATE_C, 0f);
		map.put(TransformationType.ROTATE_Z, 0f);
		map.put(TransformationType.SCALE, 1f);
		map.put(TransformationType.TRANSFORM, 0f);
	}
    public static void main( String [] args ) {
        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        final GLCanvas glcanvas = new GLCanvas( glcapabilities );
        initMap();
        
        glcanvas.addGLEventListener(new GLEventListener() {
            
            @Override //when window was resized
            public void reshape(GLAutoDrawable glautodrawable, int x, int y, int width, int height) {}
            
            @Override //work only once in the begining
            public void init(GLAutoDrawable glautodrawable) {
            	GL2 gl2 = glautodrawable.getGL().getGL2();
                gl2.glMatrixMode(GL2.GL_PROJECTION);  
                gl2.glLoadIdentity();  

                GLU glu = new GLU(); 
                glu.gluOrtho2D( 0.0f, 640, 0.0f, 680 );  

                gl2.glMatrixMode( GL2.GL_MODELVIEW );   
                gl2.glLoadIdentity();
            }
            
            @Override  //work only once at the end
            public void dispose(GLAutoDrawable glautodrawable) {}
            
            @Override 
            public void display(GLAutoDrawable glautodrawable) {
            	System.out.println("transform " + map.get(TransformationType.TRANSFORM));
            	System.out.println("scale " + map.get(TransformationType.SCALE));
            	System.out.println("rotate_c " + map.get(TransformationType.ROTATE_C));
            	GL2 gl2 = glautodrawable.getGL().getGL2();
            	gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
            	gl2.glLoadIdentity(); 
            	
            	if(map.get(TransformationType.ROTATE_C) != 0f) {
            		GLU glu = new GLU(); 
                	gl2.glRotatef(map.get(TransformationType.ROTATE_C), 0f, 0f, 1f);
            	}
            	
            	gl2.glTranslatef(220f, 90, 0f);
            	gl2.glScalef(map.get(TransformationType.SCALE), map.get(TransformationType.SCALE), 1);
            	gl2.glTranslatef(map.get(TransformationType.TRANSFORM), map.get(TransformationType.TRANSFORM), 1);
            	
            	//gl2.glRotatef(map.get(TransformationType.ROTATE_Z), 0f, 0f, 1f);
                drawK(gl2, new Random());
                drawP(gl2, new Random());
            }
        });

        final Frame frame = new Frame("KP");
        frame.add(glcanvas);
        glcanvas.addKeyListener(new KeyListener() {
			
        	@Override
        	public void keyPressed(KeyEvent e) {
        		switch (e.getKeyCode()) {
				case 37:
					map.put(TransformationType.SCALE, map.get(TransformationType.SCALE)+0.01f);
					glcanvas.display();
					break;
				case 38:
					map.put(TransformationType.TRANSFORM, map.get(TransformationType.TRANSFORM)+1f); 
					glcanvas.display();
					break;
				case 39:
					map.put(TransformationType.ROTATE_Z, map.get(TransformationType.ROTATE_Z)+1f); 
					glcanvas.display();
					break;
				
				case 40:
					map.put(TransformationType.ROTATE_C, map.get(TransformationType.ROTATE_C)+1f); 
					glcanvas.display();
					break;
				default:
					break;
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
}