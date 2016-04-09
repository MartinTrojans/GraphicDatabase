package dbhw;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.JPanel;

import java.awt.event.*;

public class Painting extends JPanel{
	
	public Graphics2D g2 = null;
	public int[] sl = null;
	public int[] sp = null;
	
	public Painting(){
		setSize(600, 600);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		dbo db = new dbo();
		int[] lions = db.getLions();
		int[] regions = db.getRegions();
		int[] ponds = db.getPonds();
		
		paintRegions(regions);
		paintLions(lions);
		paintPonds(ponds);
		if(sl != null){
			paintSelectedLions(sl);
		}
		if(sp != null){
			paintSelectedPonds(sp);
		}
	}
	
	
	public void paintLion(int x, int y){
		Ellipse2D.Double e = new Ellipse2D.Double(x, y, 5, 5);
		g2.setPaint(Color.green);
		g2.fill(e);
	}
	
	public void selectLion(int x, int y){
		Ellipse2D.Double e = new Ellipse2D.Double(x, y, 5, 5);
		g2.setPaint(Color.red);
		g2.fill(e);
	}
	
	public void paintLions(int[] ls){
		for (int i = 0; i < ls.length;){
			paintLion(ls[i], ls[i+1]);
			i += 2;
		}
	}
	
	public void paintSelectedLions(int[] ls){
		for (int i = 0; i < ls.length;){
			selectLion(ls[i], ls[i+1]);
			i += 2;
		}
	}
	
	public void paintRegion(int[] x, int[] y){
		Polygon p = new Polygon(x, y, 4);
		g2.setPaint(Color.black);
		g2.draw(p);
		//g2.setPaint(Color.white);
		//g2.fill(p);
	}
	
	public void paintRegions(int[] rs){
		int[] x = {0, 0, 0, 0};
		int[] y = {0, 0, 0, 0};
		g2.setPaint(Color.white);
		g2.fillRect(0, 0, 500, 500);
		for (int i = 0; i < rs.length;){
			x[0] = rs[i++];
			y[0] = rs[i++];
			x[1] = rs[i++];
			y[1] = rs[i++];
			x[2] = rs[i++];
			y[2] = rs[i++];
			x[3] = rs[i++];
			y[3] = rs[i++];
			//x[4] = rs[i++];
			//y[4] = rs[i++];
			i += 2;
			paintRegion(x, y);
		}
	}
	
	public void paintPond(int x, int y, int r){
		Ellipse2D.Double e = new Ellipse2D.Double(x, y, r, r);
		g2.setPaint(Color.blue);
		g2.fill(e);
		g2.setPaint(Color.black);
		g2.draw(e);
	}
	
	public void selectPond(int x, int y, int r){
		Ellipse2D.Double e = new Ellipse2D.Double(x, y, r, r);
		g2.setPaint(Color.red);
		g2.fill(e);
	}
	
	public void paintPonds(int[] ps){
		int x = 0;
		int y = 0;
		int r = 0;
		for (int i = 0; i < ps.length;){
			x = ps[i + 2];
			y = ps[i + 1];
			r = ps[i + 3] - ps[i + 1];
			i += 6;
			paintPond(x, y, r);
		}
	}
	
	public void paintSelectedPonds(int[] ps){
		int x = 0;
		int y = 0;
		int r = 0;
		for (int i = 0; i < ps.length;){
			x = ps[i + 2];
			y = ps[i + 1];
			r = ps[i + 3] - ps[i + 1];
			i += 6;
			selectPond(x, y, r);
		}
	}
	/*
	public void paintPonds(int[] ps){
		int x = 0;
		int y = 0;
		int r = 0;
		for (int i = 0; i < ps.length;){
			x = (ps[i] + ps[i+2] + ps[i+4]) / 3;
			y = (ps[i+1] + ps[i+3] + ps[i+5]) / 3;
			r = (ps[i] - x) * (ps[i] - x) + (ps[i+1] - y) * (ps[i+1] - y);
			r = (int)Math.sqrt(r);
			i += 6;
			paintPond(x, y, r);
		}
	}*/
	
	
}
