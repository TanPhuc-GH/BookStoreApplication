package com.hcmute.bookstoreapplication.services.cart;

import com.hcmute.bookstoreapplication.dtos.CartDTO;
import com.hcmute.bookstoreapplication.dtos.request.ItemRequestDTO;
import com.hcmute.bookstoreapplication.dtos.response.BaseResponse;
import com.hcmute.bookstoreapplication.entities.*;
import com.hcmute.bookstoreapplication.exceptions.NotFoundException;
import com.hcmute.bookstoreapplication.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductImageRepository productImageRepository;

    @Override
    public CartDTO getCart(Integer user_id) {
        Cart cart = cartRepository.findByUserId(user_id);
        if (cart == null){
            throw new NotFoundException("Do not have products in your cart");
        }
        List<Item> item = itemRepository.findByCartId(cart.getId());
        cart.setItems(item);
        CartDTO cartDTO = new CartDTO(cart);
        return cartDTO;
    }

    @Override
    public BaseResponse createItem(ItemRequestDTO itemRequestDTO) {
        Optional<Cart> cart = Optional.ofNullable(cartRepository.findByUserId(itemRequestDTO.getUser_id()));
        Optional<User> user = userRepository.findById(itemRequestDTO.getUser_id());
        ProductImage productImage = productImageRepository.getThumbnail(itemRequestDTO.getProduct_id());

        if (!user.isPresent()) {
            throw new NotFoundException(String.format("User with id %d not found.", itemRequestDTO.getUser_id()));
        }
        if (!cart.isPresent()) {
            Cart newCart = new Cart();
            newCart.setUser(user.get());
            cartRepository.save(newCart);
            cart = Optional.of(newCart);
        }
        if (productImage == null){
            throw new NotFoundException("Not found product image");
        }

        Optional<Product> product = productRepository.findById(itemRequestDTO.getProduct_id());
        if (!product.isPresent()) {
            throw new NotFoundException(String.format("Product with id %d not found.", itemRequestDTO.getProduct_id()));
        }
        int quantityProduct = product.get().getQuantity();
        Optional<Item> itemOptional = Optional.ofNullable(itemRepository.findByProductIdAndCartId(itemRequestDTO.getProduct_id(), cart.get().getId()));
        Item item = new Item();
        if (!itemOptional.isPresent()) {
            if (itemRequestDTO.getQuantity() <= quantityProduct) {
                item.setProduct(product.get());
                item.setItemName(product.get().getProductName());
                item.setPrice(product.get().getPrice());
                item.setThumbnail(productImage.getPath());
                item.setQuantity(itemRequestDTO.getQuantity());
                item.setCart(cart.get());
            } else throw new RuntimeException("Quantity of product is larger than in warehouse");
        } else {
            int totalQuantity = itemOptional.get().getQuantity() + itemRequestDTO.getQuantity();
            if (totalQuantity <= quantityProduct) {
                item = itemOptional.get();
                item.setQuantity(totalQuantity);
            } else throw new RuntimeException("Quantity of item no more than product quantity");
        }
        itemRepository.save(item);
        return new BaseResponse(true, "Adding product to cart successfully");
    }
}



//    @Override
//    public CartDTO createItem(ItemRequestDTO itemRequestDTO) {
//        // Tìm cart của user
//        Cart cart = cartRepository.findByUserId(itemRequestDTO.getUser_id()).orElseGet(() -> {
//            User user = userRepository.findById(itemRequestDTO.getUser_id())
//                    .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found.", itemRequestDTO.getUser_id())));
//            Cart newCart = new Cart();
//            newCart.setUser(user);
//            return cartRepository.save(newCart);
//        });
//
//        // Tìm sản phẩm
//        Product product = productRepository.findById(itemRequestDTO.getProduct_id())
//                .orElseThrow(() -> new NotFoundException(String.format("Product with id %d not found.", itemRequestDTO.getProduct_id())));
//
//        // Kiểm tra số lượng sản phẩm trong kho
//        int quantityProduct = product.getQuantity();
//        int requestQuantity = itemRequestDTO.getQuantity();
//        if (requestQuantity > quantityProduct) {
//            throw new RuntimeException("Quantity of product is larger than in warehouse");
//        }
//
//        // Tìm item trong cart (nếu có)
//        Item item = itemRepository.findByProductIdAndCartId(itemRequestDTO.getProduct_id(), cart.getId())
//                .orElseGet(() -> {
//                    Item newItem = new Item();
//                    newItem.setProduct(product);
//                    newItem.setItemName(product.getProductName());
//                    newItem.setPrice(product.getPrice());
//                    newItem.setCart(cart);
//                    return newItem;
//                });
//
//        // Cập nhật số lượng item
//        int totalQuantity = item.getQuantity() + requestQuantity;
//        if (totalQuantity > quantityProduct) {
//            throw new RuntimeException("Quantity of item no more than product quantity");
//        }
//        item.setQuantity(totalQuantity);
//        itemRepository.save(item);
//
//        // Tạo danh sách item để trả về
//        List<Item> itemList = Collections.singletonList(item);
//        CartDTO cartDTO = new CartDTO(cart);
//        cartDTO.setItems(itemList);
//        return cartDTO;
//    }
