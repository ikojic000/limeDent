package controller.observer;


/**
 *
 * @author ikojic000
 *
 *         Interface used by NovaPondaController for observing changes in the
 *         tableModel.
 *
 */
public interface OfferTblPreviewObserver {
	
	/**
	 *
	 * Method that updates user UI after table model data is changed.
	 */
	void tableModelDataChanged();
	
}
