package dbhw;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class GUI extends JFrame {
	
	private JCheckBox jcb = new JCheckBox("Show lions and ponds in selected region");
	private Painting painting = new Painting();
	private dbo db = new dbo();
	private java.awt.event.MouseListener ml = null;
	
	public GUI(){
		super("Database hw5 extra");
		this.setSize(800, 800);
		init();
		//painting.setBackground(Color.white);
	}
	
	private void init(){
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jcb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JCBactionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(painting);
        painting.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jcb)
                    .addComponent(painting, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painting, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
	}
	
	private void JCBactionPerformed(ActionEvent evt) {
        JCheckBox cb = (JCheckBox) evt.getSource();
        if (cb.isSelected()){
        	changeColor();
        } else{
        	resetColor();
        }
    }
	
	private void changeColor(){
		ml = new java.awt.event.MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int x = e.getX();
				int y = e.getY();
				//System.out.println(x);
				//System.out.println(y);
				String rid = db.getSelectedRegion(x, y);
				int[] lions = db.getSelectedLions(rid);
				int[] ponds = db.getSelectedPonds(rid);
				painting.sl = lions;
				painting.sp = ponds;
				painting.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		painting.addMouseListener(ml);
	}
	
	private void resetColor(){
		painting.removeMouseListener(ml);
		painting.sl = null;
		painting.sp = null;
		painting.repaint();
	}
	
}
