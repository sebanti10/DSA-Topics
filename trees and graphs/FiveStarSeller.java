import java.util.*;

class Product {
	public int fiveStarProducts;
	public int totalProducts;
	public double rating;
	public double increase;

	Product() {

	}

	Product(int fiveStarProducts, int totalProducts) {
		this.fiveStarProducts = fiveStarProducts;
		this.totalProducts = totalProducts;
		this.rating = (double) fiveStarProducts / totalProducts;
		this.increase = (double) (fiveStarProducts + 1) / (totalProducts + 1) - this.rating;
	}
}

class FiveStarSeller {
	public static int fiveStarReviews(List<List<Integer>> ratings, int threshold) {
		double totalRating = 0.0;
		int noOfProducts = ratings.size();

		Queue<Product> ratingPriority = new PriorityQueue<>(new Comparator<Product>() {
			public int compare (Product p1, Product p2) {
				if (p1.increase < p2.increase)
					return 1;
				else if (p1.increase > p2.increase)
					return -1;
				else
					return 0;
			}
		});

		for (List<Integer> rating: ratings) {
			Product product = new Product(rating.get(0), rating.get(1));
			ratingPriority.add(product);
			totalRating += product.rating;
		}

		int percentage = (int)((double)(totalRating / noOfProducts) * 100);
		int productsAdded = 0;

		while(!ratingPriority.isEmpty() && percentage < threshold) {
			productsAdded++;

			Product product = ratingPriority.poll();
			totalRating += product.increase;
			percentage = (int)((double)(totalRating / noOfProducts) * 100);

			if (percentage == threshold)
				return productsAdded;

			Product newProduct = new Product(product.fiveStarProducts + 1, product.totalProducts + 1);
			ratingPriority.add(newProduct);
		}
		return productsAdded;
	}

	public static void main(String[] args) {
		List<List<Integer>> ratings = new ArrayList<List<Integer>>();
		ratings.add(Arrays.asList(4, 4));
		ratings.add(Arrays.asList(1, 2));
		ratings.add(Arrays.asList(3, 6));
		int threshold = 77;
		System.out.println(fiveStarReviews(ratings, threshold));
	}
}

// 4 / 4 + 1 / 2 + 3 / 6
// = 1 + 0.5 + 0.5 = 2.0 + 0.1666667 = 2.1666667 + 0.0833 + 0.07
// = 66.66% = 72.2223% = 74.99% = 77.33%

// 5 / 5 - 4 / 4
// = 0

// 2 / 3 - 1 / 2
// = 0.1666667

// 4 / 7 - 3 / 6
// = 0.07

// 3 / 4 - 2 / 3
// = 0.0833

// 4 / 5 - 3 / 4
// = 0.05

// 5 / 8



