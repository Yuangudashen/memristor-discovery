/**
 * Memristor-Discovery is distributed under the GNU General Public License version 3
 * and is also available under alternative licenses negotiated directly
 * with Knowm, Inc.
 *
 * Copyright (c) 2016-2017 Knowm Inc. www.knowm.org
 *
 * This package also includes various components that are not part of
 * Memristor-Discovery itself:
 *
 * * `Multibit`: Copyright 2011 multibit.org, MIT License
 * * `SteelCheckBox`: Copyright 2012 Gerrit, BSD license
 *
 * Knowm, Inc. holds copyright
 * and/or sufficient licenses to all components of the Memristor-Discovery
 * package, and therefore can grant, at its sole discretion, the ability
 * for companies, individuals, or organizations to create proprietary or
 * open source (even if not GPL) modules which may be dynamically linked at
 * runtime with the portions of Memristor-Discovery which fall under our
 * copyright/license umbrella, or are distributed under more flexible
 * licenses than GPL.
 *
 * The 'Knowm' name and logos are trademarks owned by Knowm, Inc.
 *
 * If you have any questions regarding our licensing policy, please
 * contact us at `contact@knowm.org`.
 */
package org.knowm.memristor.discovery.gui.mvc.experiments.pulse.control;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import org.knowm.memristor.discovery.gui.mvc.experiments.ExperimentControlPanel;
import org.knowm.memristor.discovery.gui.mvc.experiments.ExperimentPreferences.Waveform;
import org.knowm.memristor.discovery.gui.mvc.experiments.pulse.PulsePreferences;
import org.knowm.memristor.discovery.utils.Util;

/**
 * Provides controls for running the control
 *
 * @author timmolter
 */
public class ControlPanel extends ExperimentControlPanel {

  private final JLabel appliedAmplitudeLabel;
  private final JLabel currentLabel;
  private final JLabel energyLabel;
  private final JLabel energyMemRistorOnlyLabel;

  private JComboBox<Waveform> waveformComboBox;

  private final JCheckBox memristorVoltageCheckBox;

  private final JSlider amplitudeSlider;
  private final JSlider pulseWidthSlider;
  private final JSlider pulseWidthSliderNs;

  private final JLabel seriesLabel;
  private final JTextField seriesTextField;

  private final JSlider pulseNumberSlider;

  /**
   * Constructor
   */
  public ControlPanel() {

    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

    c.gridx = 0;

    appliedAmplitudeLabel = new JLabel("Applied Amplitude [V]: ");
    appliedAmplitudeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    c.gridy++;
    c.insets = new Insets(0, 10, 4, 0);
    add(appliedAmplitudeLabel, c);

    currentLabel = new JLabel("Current [" + PulsePreferences.CURRENT_UNIT.getLabel() + "]: ");
    currentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    c.gridy++;
    c.insets = new Insets(0, 10, 4, 0);
    add(currentLabel, c);

    energyLabel = new JLabel("Energy M+R [nJ]: ");
    energyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    c.gridy++;
    c.insets = new Insets(0, 10, 4, 0);
    add(energyLabel, c);

    energyMemRistorOnlyLabel = new JLabel("Energy M [nJ]: ");
    energyMemRistorOnlyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    c.gridy++;
    c.insets = new Insets(0, 10, 4, 0);
    add(energyMemRistorOnlyLabel, c);

    this.waveformComboBox = new JComboBox<>();
    waveformComboBox.setFocusable(false);
    c.gridy++;
    c.insets = new Insets(0, 0, 4, 6);
    add(waveformComboBox, c);

    c.gridy++;
    c.insets = new Insets(0, 0, 0, 0);
    memristorVoltageCheckBox = new JCheckBox("Memristor Voltage Drop");
    add(memristorVoltageCheckBox, c);

    amplitudeSlider = new JSlider(JSlider.HORIZONTAL, -250, 200, 0);
    amplitudeSlider.setBorder(BorderFactory.createTitledBorder("Amplitude [V]"));
    amplitudeSlider.setMajorTickSpacing(50);
    amplitudeSlider.setMinorTickSpacing(10);
    amplitudeSlider.setPaintTicks(true);
    amplitudeSlider.setPaintLabels(true);
    amplitudeSlider.setSnapToTicks(true);
    Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
    labelTable.put(-250, new JLabel("-2.5"));
    labelTable.put(-200, new JLabel("-2"));
    labelTable.put(-100, new JLabel("-1"));
    labelTable.put(0, new JLabel("0"));
    labelTable.put(100, new JLabel("1"));
    labelTable.put(200, new JLabel("2"));
    amplitudeSlider.setLabelTable(labelTable);
    c.gridy++;
    c.insets = new Insets(0, 6, 4, 6);
    amplitudeSlider.setPreferredSize(new Dimension(300, 80));
    add(amplitudeSlider, c);

    pulseWidthSlider = new JSlider(JSlider.HORIZONTAL, 5000, 100000, 5000);
    pulseWidthSlider.setBorder(BorderFactory.createTitledBorder("Pulse Width [µs]"));
    pulseWidthSlider.setMinorTickSpacing(5000);
    pulseWidthSlider.setPaintTicks(true);
    pulseWidthSlider.setPaintLabels(true);
    pulseWidthSlider.setSnapToTicks(true);
    labelTable = new Hashtable<>();
    labelTable.put(5000, new JLabel("5"));
    labelTable.put(50000, new JLabel("50"));
    labelTable.put(100000, new JLabel("100"));
    pulseWidthSlider.setLabelTable(labelTable);
    c.gridy++;
    add(pulseWidthSlider, c);

    pulseWidthSliderNs = new JSlider(JSlider.HORIZONTAL, 500, 5000, 5000);
    pulseWidthSliderNs.setBorder(BorderFactory.createTitledBorder("Pulse Width [µs]"));
    pulseWidthSliderNs.setMinorTickSpacing(500);
    pulseWidthSliderNs.setPaintTicks(true);
    pulseWidthSliderNs.setPaintLabels(true);
    pulseWidthSliderNs.setSnapToTicks(true);
    labelTable = new Hashtable<>();
    labelTable.put(500, new JLabel(".5"));
    labelTable.put(1000, new JLabel("1"));
    labelTable.put(2000, new JLabel("2"));
    labelTable.put(3000, new JLabel("3"));
    labelTable.put(4000, new JLabel("4"));
    labelTable.put(5000, new JLabel("5"));
    pulseWidthSliderNs.setLabelTable(labelTable);
    c.gridy++;
    add(pulseWidthSliderNs, c);

    pulseNumberSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
    pulseNumberSlider.setBorder(BorderFactory.createTitledBorder("Pulse Number"));
    pulseNumberSlider.setMinorTickSpacing(1);
    pulseNumberSlider.setPaintTicks(true);
    pulseNumberSlider.setPaintLabels(true);
    pulseNumberSlider.setSnapToTicks(true);
    labelTable = new Hashtable<>();
    labelTable.put(1, new JLabel("1"));
    labelTable.put(5, new JLabel("5"));
    labelTable.put(10, new JLabel("10"));
    pulseNumberSlider.setLabelTable(labelTable);
    c.gridy++;
    add(pulseNumberSlider, c);

    seriesLabel = new JLabel("Series R [Ohm]");
    seriesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    c.gridy++;
    c.insets = new Insets(0, 10, 4, 0);
    add(seriesLabel, c);

    seriesTextField = new JTextField();
    seriesTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
    c.gridy++;
    c.insets = new Insets(0, 5, 14, 5);
    add(seriesTextField, c);

    startStopButton = new JButton("Start");
    startStopButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    c.gridy++;
    c.insets = new Insets(0, 0, 0, 0);
    add(startStopButton, c);

    c.gridy++;
    JLabel logoLabel = new JLabel(Util.createImageIcon("img/logo_200.png"));
    add(logoLabel, c);
  }

  public void enableAllChildComponents(boolean enabled) {

    waveformComboBox.setEnabled(enabled);
    memristorVoltageCheckBox.setEnabled(enabled);
    amplitudeSlider.setEnabled(enabled);
    pulseWidthSlider.setEnabled(enabled);
    pulseWidthSliderNs.setEnabled(enabled);
    pulseNumberSlider.setEnabled(enabled);
    seriesTextField.setEnabled(enabled);
    startStopButton.setEnabled(enabled);
  }

  public void updateEnergyGUI(double appliedAmplitude, double appliedCurrent, double appliedEnergy, double appliedMemristorEnergy) {

    appliedAmplitudeLabel.setText("Applied Amplitude [V]: " + Util.round(appliedAmplitude, 2));
    currentLabel.setText("Current [" + PulsePreferences.CURRENT_UNIT.getLabel() + "]: " + Util.round(appliedCurrent, 3));
    energyLabel.setText("Energy M+R [nJ]: " + Util.round(appliedEnergy, 3));
    energyMemRistorOnlyLabel.setText("Energy M [fJ]: " + Util.round(appliedMemristorEnergy, 3));
  }

  public JComboBox<Waveform> getWaveformComboBox() {

    return waveformComboBox;
  }

  public JSlider getAmplitudeSlider() {

    return amplitudeSlider;
  }

  public JSlider getPulseWidthSlider() {

    return pulseWidthSlider;
  }

  public JSlider getPulseWidthSliderNs() {

    return pulseWidthSliderNs;
  }

  public JSlider getPulseNumberSlider() {

    return pulseNumberSlider;
  }

  public JTextField getSeriesTextField() {

    return seriesTextField;
  }

  public JCheckBox getMemristorVoltageCheckBox() {

    return memristorVoltageCheckBox;
  }
}
