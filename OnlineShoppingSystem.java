import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class OnlineShoppingSystem{
	
	public static void addingProducts(Order customer) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Press Y to start Shopping");
		String opt = scan.next();
		
		while(opt.contentEquals("N") == false) {
		   System.out.println("Please choose the product you wish to buy from the list below : ");
		   System.out.println("Press A to buy Soap");
		   System.out.println("Press B to buy Shampoo");
		   System.out.println("Press C to buy Hair Oil");
		   String option = scan.next();
		   System.out.println("How much do you wish to buy : ");
		   int quan = scan.nextInt();
		   
		   if(option.contentEquals("A")) {
			   customer.addProduct(new Product("Pro-11001", "Soap" , quan , quan* 20));
		   }
		   else if(option.equals("B")) {
			   customer.addProduct(new Product("Pro-11002", "Shampoo" , quan , quan* 60));
		   }
		   else if(option.equals("C")) {
			   customer.addProduct(new Product("Pro-11003", "Hair Oil" , quan , quan* 40));
		   }
		   else {
			   System.out.println("Product not found");
		   }
		   System.out.println("If you wish to continue Shopping");
		   System.out.println("Press Y else N ");
		   opt = scan.next();
	   }
	}
	
	public static void removingProducts(Order customer) throws Exception{
		Scanner scan = new Scanner(System.in);
		System.out.println("If you want to remove any product. Press Y else N");
		String opt = scan.next();
		
		while(opt.contentEquals("N") == false) {
			System.out.println("Please choose the product you wish to remove :");
			System.out.println("Press A to remove Soap");
			   System.out.println("Press B to remove Shampoo");
			   System.out.println("Press C to remove Hair Oil");
			   String option = scan.next();
			   
			   if(option.contentEquals("A")) {
				   customer.removeProduct("Pro-11001");
			   }
			   else if(option.equals("B")) {
				   customer.removeProduct("Pro-11002");
			   }
			   else if(option.equals("C")) {
				   customer.removeProduct("Pro-11003");
			   }
			   else {
				   System.out.println("Product not found");
			   }
			   System.out.println("If you wish to remove more products");
			   System.out.println("Press Y else N ");
			   opt = scan.next();
		}
		System.out.println();
		System.out.println("=================================");
		System.out.println();
	}
	
	public static void main(String args[]) throws Exception{
		Scanner s = new Scanner(System.in);
		System.out.print("Please enter your name : ");
		String user = s.next();
		Order customer = new Order(user);
		
		addingProducts(customer);
		removingProducts(customer);
		
		System.out.println("Thankyou for Shopping, " + user);
		System.out.println();
	    System.out.println ("Your Cart Total is : "+ customer.getCartPrice());   
	    final Iterator<Product> it = customer.getCartDetails().iterator();
	    while(it.hasNext()){		
		  System.out.println (it.next());
		}
	}
}

class Product{
	
	public String pname;
	public String pid;
	public int qty;
	public double price;

	public Product(){}
	public Product(String pid, String pname, int qty, double price) {
		this.pid = pid;
		this.pname = pname;
		this.qty = qty;
		this.price = price;
	}
	
	public void setPid(String pid) {
		this.pid = pid; 
	}

	public void setPname(String pname) {
		this.pname = pname; 
	}

	public void setQty(int qty) {
		this.qty = qty; 
	}

	public void setPrice(double price) {
		this.price = price; 
	}

	public String getPid() {
		return (this.pid); 
	}

	public String getPname() {
		return (this.pname); 
	}

	public int getQty() {
		return (this.qty); 
	}

	public double getPrice() {
		return (this.price); 
	}

	public String toString() {
		String sep = System.getProperty("line.separator");
		StringBuffer buffer = new StringBuffer();
		buffer.append(sep);
		buffer.append("----- Product Detail ----- ");
		buffer.append(sep);
    buffer.append("\tpid = ");
		buffer.append(pid);
		buffer.append(sep);
		buffer.append("\tpname = ");
		buffer.append(pname);
		buffer.append(sep);
		buffer.append("\tqty = ");
		buffer.append(qty);
		buffer.append(sep);
		buffer.append("\tprice = ");
		buffer.append(price);
		buffer.append(sep);
		return buffer.toString();
	}
}

class ProductNotFoundException extends Exception {
	public ProductNotFoundException(){}
	public ProductNotFoundException(String msg){
		super(msg);
	}
}


 class Order{
	
	public String userid;
	private Map<String, Product> map;
	
	public Order(){}
	public Order(String uid){
		this.userid = userid;
		map = new HashMap<String, Product>();
	}
	
	public void addProduct(Product p){
		if(map.containsKey(p.getPid())){
			Product p1 = map.get(p.getPid());
			p1.setPrice(p1.getPrice()+p.getPrice());
			p1.setQty(p1.getQty()+p.getQty());
			return;	
		}
		map.put(p.getPid(),p);
	}
	
	public void removeProduct(String pid){
		if(map.containsKey(pid)){
			map.remove(pid);
			return;
		}
		else{
			try {
			    throw new ProductNotFoundException(pid);
	        }catch(ProductNotFoundException e) {
		        System.out.println("Product with ID "+pid+" is not Found.");
	        }
	    }
	}
	
	public Collection<Product> getCartDetails(){
		return map.values();
	}
	
	public int productCount(){
		return map.size();
	}
  
    public double getCartPrice() {
      double price = 0.0d;
      Iterator<Product> iterator = getCartDetails().iterator();
      while(iterator.hasNext()){
         price += iterator.next().getPrice();
      }
      return price;
    }
}
 