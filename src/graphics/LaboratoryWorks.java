package graphics;
import java.awt.Frame;
import java.awt.Point;
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
		map.put(TransformationType.CUT, 0f);
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
              //  gl2.glClearColor(1, 0, 0, 1); // background color

                GLU glu = new GLU(); 
                glu.gluOrtho2D( 0.0f, 640, 0.0f, 680 );  

                gl2.glMatrixMode( GL2.GL_MODELVIEW );   
                gl2.glLoadIdentity();
            }
            
            @Override  //work only once at the end
            public void dispose(GLAutoDrawable glautodrawable) {}
            
            @Override 
            public void display(GLAutoDrawable glautodrawable) {
            	GL2 gl2 = glautodrawable.getGL().getGL2();
            	gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
            	gl2.glLoadIdentity();
            	
            	gl2.glRotatef(map.get(TransformationType.ROTATE_Z), 0f, 0f, 1f); 
            	gl2.glTranslatef(220f, 90, 0f);
            	gl2.glTranslatef(map.get(TransformationType.TRANSFORM), map.get(TransformationType.TRANSFORM), 1);
            	gl2.glScalef(map.get(TransformationType.SCALE), map.get(TransformationType.SCALE), 1);
            	gl2.glRotatef(map.get(TransformationType.ROTATE_C), 0f, 0f, 1f);
            	gl2.glTranslatef(-220f, -90, 0f);
            	
            	if(map.get(TransformationType.CUT) == 1f) {
            		double[] eqn0 = { 1.0, 0.0, 0.0, -100.0};
            		gl2.glClipPlane(GL2.GL_CLIP_PLANE0, eqn0, 0);
            		gl2.glEnable(GL2.GL_CLIP_PLANE0);
            		double[] eqn1 = { 0.0, 1.0, 0.0, -50.0};
            		gl2.glClipPlane(GL2.GL_CLIP_PLANE1, eqn1, 0);
            		gl2.glEnable(GL2.GL_CLIP_PLANE1);
            		double[] eqn2 = { -1.0, 0.0, 0.0, 300.0};
            		gl2.glClipPlane(GL2.GL_CLIP_PLANE2, eqn2, 0);
            		gl2.glEnable(GL2.GL_CLIP_PLANE2);
            		double[] eqn3 = { 0.0, -1.0, 0.0, 200.0};
            		gl2.glClipPlane(GL2.GL_CLIP_PLANE3, eqn3, 0);
            		gl2.glEnable(GL2.GL_CLIP_PLANE3);
            	}
            	
                drawK(gl2, new Random());
                drawSplainedP(gl2, new Random());
                gl2.glDisable(GL2.GL_CLIP_PLANE0);
                gl2.glDisable(GL2.GL_CLIP_PLANE1);
                gl2.glDisable(GL2.GL_CLIP_PLANE2);
                gl2.glDisable(GL2.GL_CLIP_PLANE3);
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
				case 16: 
					map.put(TransformationType.CUT, (map.get(TransformationType.CUT) == 1f)? 0f : 1f);
					glcanvas.display();
					break;
				default: 
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
    
    protected static void drawSplainedP(GL2 gl2, Random random){
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
    	
    	Point points[] = new Point[]{
    			new Point(360, 230), 
    			new Point(380, 230),
    			new Point(390, 200),
    			new Point(380, 120),
    			new Point(360, 120),
    			new Point(330, 110),
    			new Point(280, 130),
    			new Point(280, 140),
    			new Point(290, 150),
    			new Point(300, 150),
    			new Point(310, 155),
    			new Point(320, 160),
    			new Point(340, 165)};
    	splain(points, gl2, r);
    }
    
    private static void splain(Point p[], GL2 gl2, Random r) {
    	gl2.glBegin(GL.GL_LINE_LOOP);
    	int n = p.length;
    	for (int i = 2; i < n+1; i++) {
    		float xA = p[(i-1) % n].x,
    			  xB = p[(i) % n].x,
    			  xC = p[(i + 1) % n].x,
    			  xD = p[(i + 2) % n].x,
    			  yA = p[(i - 1) % n].y,
    			  yB = p[(i) % n].y,
    			  yC = p[(i + 1) % n].y,
    			  yD = p[(i + 2) % n].y,
    			  a3 = (-xA + 3 * (xB -xC) + xD) / 6, 
    			  a2 = (xA - 2 * xB + xC) / 2,
    			  a1 = (xC - xA) / 2,
    			  a0 = (xA + 4 * xB + xC) / 6,
    			  b3 = (-yA + 3 * (yB - yC) + yD ) / 6,
    			  b2 = (yA - 2 * yB + yC) / 2,
    			  b1 = (yC - yA) / 2,
    			  b0 = (yA + 4 * yB + yC) / 6;
    	
    		for(int j = 0; j < n; j++) {
    			float t = j / (float) n,
    				  x = ((a3 * t + a2) * t + a1 ) * t + a0,
    				  y = ((b3 * t + b2) * t + b1 ) * t + b0;
    			gl2.glColor3f( r.nextFloat(), r.nextFloat(), r.nextFloat());
    			gl2.glVertex2f(x, y);
    		}
    			  
    	}
    	gl2.glEnd();
    					  
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