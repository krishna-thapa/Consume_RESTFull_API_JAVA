package com.kthapa.flexionmobile.model;

import com.flexionmobile.codingchallenge.integration.Purchase;

/**
 * Created by Krishna Thapa on 19/12/2018
 */
public class PurchaseImpl implements Purchase{

	private String id;
    private boolean consumed;
    private String itemId;

    public PurchaseImpl(){

	}

    @Override
	public String getId() {
		return id;
	}

	@Override
	public String getItemId() {
		return itemId;
	}
	
	@Override
	public boolean getConsumed() {
		return consumed;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
}
