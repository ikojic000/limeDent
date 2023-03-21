package controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

import controller.command.DeleteProductCommand;
import controller.command.ProductCommand;
import dao.ProductDAO;
import model.Product;
import model.tableModels.ProductTableModel;
import view.viewPanel.UslugePanel;


public class UslugeController {
	
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ProductTableModel tableModel;
	private UslugePanel uslugePanel;
	private ProductDAO productDAO;
	private Stack<ProductCommand> undoStack = new Stack<ProductCommand>();
	
	public UslugeController( UslugePanel uslugePanel ) {
		
		this.uslugePanel = uslugePanel;
		this.productDAO = new ProductDAO();
		this.productList = getProductList();
		
	}
	
	
	public Product getSelectedProduct( int row ) {
		
		return tableModel.getProductList().get( row );
		
	}
	
	
	private ArrayList<Product> getProductList() {
		
		productList = productDAO.getAllProducts();
		return productList;
		
	}
	
	
	public void setTableData() {
		
		tableModel = new ProductTableModel( productList );
		uslugePanel.getTable().setModel( tableModel );
		
	}
	
	
	public void setSearchData() {
		
		String search = uslugePanel.getTxtSearch().getText();
		
		if ( search == null || search.isEmpty() ) {
			
			tableModel.setProductList( productList );
			
		} else {
			
			ArrayList<Product> searchList = productList.stream()
					.filter( p -> p.getName().toLowerCase().contains( search.toLowerCase() )
							|| p.getCode().toString().toLowerCase().contains( search.toLowerCase() ) )
					.collect( Collectors.toCollection( ArrayList::new ) );
			
			tableModel.setProductList( searchList );
			
		}
		
	}
	
	
//	Triba rjesit
	public void addProduct( Product product ) {
		
		productDAO.addProduct( product );
		
		getProductList();
		
		setTableData();
		
		uslugePanel.clearAll();
		
		uslugePanel.getTable().scrollRectToVisible(
				uslugePanel.getTable().getCellRect( uslugePanel.getTable().getRowCount() - 1 , 0 , true ) );
		
	}
	
	
	public void deleteProduct( int row ) {
		
		Product product = getSelectedProduct( row );
		
		ProductCommand deleteProductCommand = new DeleteProductCommand( product );
		deleteProductCommand.execute();
		undoStack.push( deleteProductCommand );
		
		tableModel.getProductList().remove( product );
		productList.remove( product );
		tableModel.setProductList( productList );
		tableModel.fireTableDataChanged();
		
		uslugePanel.clearAll();
		
	}
	
	
	public void updateProduct( int row ) {
		
		Product product = getSelectedProduct( row );
		
		product.setName( uslugePanel.getTxtNazivArtikla().getText() );
		product.setPrice( BigDecimal
				.valueOf( Double.parseDouble( uslugePanel.getTxtCijenaArtikla().getText().replace( "," , "." ) ) ) );
		
		productDAO.updateProduct( product );
		tableModel.setProductList( productList );
		tableModel.fireTableDataChanged();
		
		uslugePanel.clearAll();
		
	}
	
	
	public String undoCommandAction() {
		
		String data = "";
		
		if ( !undoStack.isEmpty() ) {
			
			ProductCommand command = undoStack.pop();
			command.undo();
			
			data = "Vratili ste izbrisanu uslugu - " + command.getProductInfo();
			
			productList.add( command.getProduct() );
			tableModel.fireTableDataChanged();
			uslugePanel.clearAll();
			
			uslugePanel.getTable().scrollRectToVisible(
					uslugePanel.getTable().getCellRect( uslugePanel.getTable().getRowCount() - 1 , 0 , true ) );
			
		} else {
			
			data = "Nemate što poništiti...";
			
		}
		
		return data;
		
	}
	
}
