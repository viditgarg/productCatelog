package com.vk.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vk.model.Category;
import com.vk.model.Product;

@Repository
public class ProductCatelogServiceImpl implements ProductCatelogService {

	Connection conn;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCatelogServiceImpl.class);

	private static final AtomicLong counter = new AtomicLong();

	private static List<Product> products;
	private static List<Category> categories;
	
	
	static {
		products = populateDummyProducts();
		categories = populateDummyCategories();
	}

	private static List<Product> populateDummyProducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(counter.incrementAndGet(), "Iphone", 2, 70000.00, "Electonic"));
		products.add(new Product(counter.incrementAndGet(), "Harry Potter", 1, 5500.00, "Book"));
		products.add(new Product(counter.incrementAndGet(), "Benetton Tshirt", 2, 1500.00, "Apparel"));
		products.add(new Product(counter.incrementAndGet(), "Soups", 3, 200.00, "Food"));
		return products;
	}
	
	private static List<Category> populateDummyCategories() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(1, "Category A", (float) 10.00));
		categories.add(new Category(2, "Category B", (float) 20.00));
		categories.add(new Category(3, "Other", (float) 0.00));
		return categories;
	}

	public ProductCatelogServiceImpl() {
		try {
			connectionToDerby();
			normalDbUsage();
		} catch (SQLException e) {
			LOGGER.error("error in setting up derby connection:");
			e.printStackTrace();
		}
	}

	public void connectionToDerby() throws SQLException {
		// -------------------------------------------
		// URL format is
		// jdbc:derby:<local directory to save data>
		// -------------------------------------------
		String dbUrl = "jdbc:derby:memory:MyDB\\demo;create=true";
		conn = DriverManager.getConnection(dbUrl);

		Statement stmt = conn.createStatement();
		// create table
		stmt.executeUpdate( "create table products (id int primary key, name varchar(50), typeId int, cost double, productCategory varchar(20))");
		
		stmt.executeUpdate("create table categories (id int primary key, name varchar (20), levyPercentage float)");
	}

	public void normalDbUsage() throws SQLException {

		String productsSQL = "insert into products values (?,?,?,?,?)";
		String categorySQL = "insert into categories values (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(productsSQL);
		Product product = null;
		Category category = null;

		Iterator it = products.iterator();

		while (it.hasNext()) {
			product = (Product) it.next();

			pstmt.setLong(1, product.getId());
			pstmt.setString(2, product.getName());
			pstmt.setInt(3, product.getCategoryTypeId());
			pstmt.setDouble(4, product.getCost());
			pstmt.setString(5, product.getProductCategory());

			// executing prepared statement to insert products into DB
			pstmt.executeUpdate();
		}
		
		it = null;
		pstmt = null;
		pstmt = conn.prepareStatement(categorySQL);
		it = categories.iterator();

		while (it.hasNext()) {
			category = (Category) it.next();
			pstmt.setInt(1, category.getId());
			pstmt.setString(2, category.getName());
			pstmt.setFloat(3, category.getLevyPercentage());
			
			pstmt.executeUpdate();
		}
		it = null;
		pstmt = null;
	}

	@Override
	public Product findById(long id) { 
		return null;
	}

	@Override
	public Product findByName(String name) {
		
		return null;
	}

	@Override
	public int findProductType(Product product) {
		
		return 0;
	}

	@Override
	public void saveProduct(Product product) {
		
	}

	@Override
	public void updateProduct(Product product) {
	

	}

	@Override
	public void deleteProductById(long id) {
		

	}

	@Override
	public List<Product> findAllProducts() {
		LOGGER.info("---in find all products method --");
		Statement stmt;
		Product product = null;
		List<Product> savedProducts = new ArrayList<Product>();
		try {
			stmt = conn.createStatement();
			// query
			ResultSet rs = stmt.executeQuery("SELECT * FROM products");

			// print out query result
			while (rs.next()) {
				
				product = new Product();
				product.setId(rs.getLong(1));
				product.setName(rs.getString(2));
				product.setCategoryTypeId(rs.getInt(3));
				
				product.setCost(rs.getDouble(4));
				product.setProductCategory(rs.getString(5));
				
				product = computeSalesTaxforProduct(product);
				
				savedProducts.add(product);
				product = null;
			}
		} catch (SQLException e) {
			LOGGER.error("Error in fetching all products...");
			e.printStackTrace();
		}
		return savedProducts;
	}

	private Product computeSalesTaxforProduct(Product product) {
		Category category = findById(product.getCategoryTypeId());
		product.setCategoryType(category.getName());
		DecimalFormat df = new DecimalFormat("#.##");
		double salesTax = product.getCost() * category.getLevyPercentage() * 0.01;
		
		product.setSalesTax(Double.valueOf(df.format(salesTax)));
		return product;
	}

	@Override
	public void deleteAllProducts() {
		

	}

	@Override
	public boolean isProductExist(Product product) {
		
		return false;
	}

	@Override
	public List<Category> findAllCategories() {
		LOGGER.info("---in findAllCategories method --");
		Statement stmt;
		Category category = null;
		List<Category> savedCategories = new ArrayList<Category>();
		try {
			stmt = conn.createStatement();
			// query
			ResultSet rs = stmt.executeQuery("SELECT * FROM categories");

			// print out query result
			while (rs.next()) {
				
				category = new Category(rs.getInt(1), rs.getString(2),rs.getFloat(3));
				
				savedCategories.add(category);
				category = null;
			}
		} catch (SQLException e) {
			LOGGER.error("Error in fetching all products...");
			e.printStackTrace();
		}
		return savedCategories;
	}

	

	@Override
	public String findProductCategory(int id) {
		LOGGER.info("---in findProductCategory method --");
		Statement stmt;
		Category category = null;
		
		try {
			stmt = conn.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public float findLevyPercentage(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Category findById(int id) {
		LOGGER.info("---in findById method --");
		Category category = new Category();
		Statement stmt;
		// query
		try {
			
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM categories where id="+id);
			
			while (rs.next()){
				category.setId(id);
				category.setName(rs.getString("name"));
				category.setLevyPercentage(rs.getFloat("levyPercentage"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		return category;
	}
	
	

}
