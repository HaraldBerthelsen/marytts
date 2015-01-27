/**
 * Copyright 2009 DFKI GmbH.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * This file is part of MARY TTS.
 *
 * MARY TTS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

/*
 * LanguagePanel.java
 *
 * Created on 17. September 2009, 16:31
 */

package marytts.tools.install;

import java.util.Locale;

/**
 * 
 * @author marc
 */
public class LanguagePanel extends javax.swing.JPanel {
	private LanguageComponentDescription desc;

	/** Creates new form LanguagePanel */
	public LanguagePanel(LanguageComponentDescription desc) {
		this.desc = desc;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of
	 * this method is always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		jLabel8 = new javax.swing.JLabel();
		lLocale = new javax.swing.JLabel();
		lName = new javax.swing.JLabel();
		lVersion = new javax.swing.JLabel();
		lNameValue = new javax.swing.JLabel();
		lLocaleValue = new javax.swing.JLabel();
		lVersionValue = new javax.swing.JLabel();
		lSize = new javax.swing.JLabel();
		lSizeValue = new javax.swing.JLabel();
		spDescription = new javax.swing.JScrollPane();
		taDescription = new javax.swing.JTextArea();
		lStatus = new javax.swing.JLabel();
		lStatusValue = new javax.swing.JLabel();

		jLabel8.setFont(new java.awt.Font("Lucida Grande", 1, 13));
		jLabel8.setText("Version:");

		setBorder(javax.swing.BorderFactory.createTitledBorder("Language " + desc.getLocale().getDisplayName(Locale.ENGLISH)));
		lLocale.setFont(new java.awt.Font("Lucida Grande", 1, 13));
		lLocale.setText("Locale:");

		lName.setFont(new java.awt.Font("Lucida Grande", 1, 13));
		lName.setText("Name:");

		lVersion.setFont(new java.awt.Font("Lucida Grande", 1, 13));
		lVersion.setText("Version:");

		lNameValue.setText(desc.getName());

		lLocaleValue.setText(desc.getLocale().toString());

		lVersionValue.setText(desc.getVersion());

		lSize.setFont(new java.awt.Font("Lucida Grande", 1, 13));
		lSize.setText("Size:");

		lSizeValue.setText(desc.getDisplayPackageSize());

		taDescription.setColumns(20);
		taDescription.setEditable(false);
		taDescription.setFont(new java.awt.Font("Courier New", 0, 10));
		taDescription.setLineWrap(true);
		taDescription.setRows(2);
		taDescription.setText(desc.getDescription());
		spDescription.setViewportView(taDescription);

		lStatus.setFont(new java.awt.Font("Lucida Grande", 1, 13));
		lStatus.setText("Status:");

		lStatusValue.setText(desc.getStatus().toString()
				+ (desc.isUpdateAvailable() ? " -- Update available to version " + desc.getAvailableUpdate().getVersion() : ""));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING).add(
				org.jdesktop.layout.GroupLayout.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap()
						.add(layout
								.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
								.add(org.jdesktop.layout.GroupLayout.LEADING, spDescription,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
								.add(layout.createSequentialGroup().add(lName)
										.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(lNameValue)
										.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(lLocale)
										.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(lLocaleValue)
										.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 90, Short.MAX_VALUE)
										.add(lVersion).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
										.add(lVersionValue).addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(lSize)
										.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(lSizeValue))
								.add(org.jdesktop.layout.GroupLayout.LEADING,
										layout.createSequentialGroup().add(lStatus)
												.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED).add(lStatusValue)))
						.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(layout
						.createSequentialGroup()
						.add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(lSizeValue).add(lSize)
								.add(lVersionValue).add(lVersion).add(lName).add(lNameValue).add(lLocale).add(lLocaleValue))
						.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
						.add(spDescription, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
						.add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE).add(lStatus).add(lStatusValue))));
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel lLocale;
	private javax.swing.JLabel lLocaleValue;
	private javax.swing.JLabel lName;
	private javax.swing.JLabel lNameValue;
	private javax.swing.JLabel lSize;
	private javax.swing.JLabel lSizeValue;
	private javax.swing.JLabel lStatus;
	private javax.swing.JLabel lStatusValue;
	private javax.swing.JLabel lVersion;
	private javax.swing.JLabel lVersionValue;
	private javax.swing.JScrollPane spDescription;
	private javax.swing.JTextArea taDescription;
	// End of variables declaration//GEN-END:variables

}
