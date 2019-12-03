package org.richfaces.event;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.FacesEvent;
import javax.faces.event.FacesListener;

import org.richfaces.model.UploadItem;

public class UploadEvent extends FacesEvent {
	private static final long serialVersionUID = -7645197191376210068L;
	private List<UploadItem> uploadItems = null;

	public UploadEvent(UIComponent component, List<UploadItem> uploadItems) {
		super(component);
		this.uploadItems = uploadItems;

	}

	@Override
	public boolean isAppropriateListener(FacesListener listener) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void processListener(FacesListener listener) {
		// TODO Auto-generated method stub

	}

	public UploadItem getUploadItem() {
		// super(component);
		this.uploadItems = uploadItems;
		return (UploadItem) uploadItems;

	}
}