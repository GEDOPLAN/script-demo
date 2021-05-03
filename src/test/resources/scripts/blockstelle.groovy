import de.gedoplan.showcase.SignalStellung;

if (gleis406.isBesetzt()) {
  signalSbk2.setStellung(SignalStellung.HALT);
} else {
  signalSbk2.setStellung(SignalStellung.FAHRT);
}
