package nl.groep2.cnl.slim_parkeren.presentation;

import org.bson.types.ObjectId;

import nl.groep2.cnl.slim_parkeren.presentation.model.ResponseView;

public class ResponsePresenter extends BasePresenter{

	public ResponseView present(ObjectId id, String message){
		ResponseView view = new ResponseView();
		view.objectId = id.toString();
		view.message = message;
		return view;
	}
}
