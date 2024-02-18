package org.socialmeli.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.socialmeli.entity.Post;
import org.socialmeli.entity.Product;
import org.socialmeli.entity.Vendor;
import org.socialmeli.entity.Client;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Repository
public class PostRepositoryImp implements IRepository<Post> {
    private List<Post> posts;
    private VendorRepositoryImp vendorRepositoryImp;
    
    public PostRepositoryImp(){
        vendorRepositoryImp = new VendorRepositoryImp();
        Product product1 = new Product(1, "Camiseta", "Ropa", "Nike", "Blanco", "Con logo");
        Product product2 = new Product(2, "Zapatos", "Calzado", "Adidas", "Negro", "N/A");
        Product product3 = new Product(3, "Bolso", "Accesorio", "Puma", "Rojo", "Cuero");
        Post post1 = new Post(1, vendorRepositoryImp.findAll().get(1).getUserId(), LocalDate.of(2023, 3, 20), product1, 1, 35.99);
        Post post2 = new Post(2, vendorRepositoryImp.findAll().get(1).getUserId(), LocalDate.now(), product2, 2, 79.99);
        Post post3 = new Post(3, vendorRepositoryImp.findAll().get(1).getUserId(), LocalDate.now(), product3, 1, 49.99);
        this.posts = new ArrayList<>(List.of(post1, post2, post3));
    }

    public List<Post> findAll() {
        return posts;
    }
    public void save(Post client) {
        posts.add(client);
    }
    public Post findOne (Integer id) {
        return posts.stream()
                .filter(client -> client.getPostId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void deleteOne(Integer id) {
        posts.removeIf(c -> c.getPostId().equals(id));
    }

    public List<Vendor> getFollowedList(Client client, List<Vendor> vendorList){
        List<Vendor> auxListVendors = new ArrayList<>();
        for(Vendor vendor: vendorList){
            for(int i=0; i<client.getFollowing().size(); i++){
                if(vendor.getUserId().intValue() == client.getFollowing().get(i).getUserId()){
                    auxListVendors.add(vendor);
                }
            }
        }
        return auxListVendors;
    }
}
