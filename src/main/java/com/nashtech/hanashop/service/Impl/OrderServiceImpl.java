package com.nashtech.hanashop.service.Impl;

import com.nashtech.hanashop.data.dto.*;
import com.nashtech.hanashop.data.entity.OrderEntity;
import com.nashtech.hanashop.data.entity.ProductEntity;
import com.nashtech.hanashop.data.entity.UserEntity;
import com.nashtech.hanashop.data.mapper.OrderDetailMapper;
import com.nashtech.hanashop.data.mapper.OrderMapper;
import com.nashtech.hanashop.repository.OrderDetailRepository;
import com.nashtech.hanashop.repository.OrderRepository;
import com.nashtech.hanashop.repository.ProductRepository;
import com.nashtech.hanashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<OrderDTO>  getAllOrderByUser(String userName){
        List<OrderDTO> listOrder = new ArrayList<>();
        UserEntity user = new UserEntity();
        user.setUserName(userName);
        List<OrderEntity> listOrderEntity = orderRepo.findByUser(user);
        if(listOrderEntity!=null){
            listOrderEntity.forEach(entity -> {
                listOrder.add(OrderMapper.parseEntityToDTO(entity));
            });
        }
        return listOrder;
    }

    public OrderDTO order(CartDTO cart){
        float total = getTotal(cart);
        OrderDTO order = new OrderDTO();
        Date date = new Date();
        String orderID = randomOrderID();
        CustomerDTO customer = (CustomerDTO) cart.getCustomer();
        String address = customer.getAddress();
        String phone = customer.getPhone();
        String typePay = "Cash";
        String username = customer.getUserName();
        order.setOrderID(orderID);
        order.setOrderDate(date);
        order.setTotalPrice(total);
        order.setTypePay(typePay);
        order.setUserName(username);
        order.setPhone(phone);
        order.setAddress(address);
        orderRepo.save(OrderMapper.parseDTOToEntity(order));
        //
        for(Map.Entry entry: cart.getCart().entrySet()){
            ProductDTO productBuy =(ProductDTO) entry.getValue();
            OrderDetailDTO detail = new OrderDetailDTO();
            detail.setOrderID(orderID);
            detail.setPrice(productBuy.getPrice()* productBuy.getQuantity());
            detail.setQuantity(productBuy.getQuantity());
            detail.setProductID(productBuy.getProductID());
            orderDetailRepository.save(OrderDetailMapper.parseDTOToEntity(detail));
            // Update product quantity
            ProductEntity entity = productRepository.findByProductID(productBuy.getProductID());
            int quantityRemain = entity.getQuantity() - productBuy.getQuantity();
            entity.setQuantity(quantityRemain);
            productRepository.save(entity);
        }
        return order;

    }

    private float getTotal(CartDTO cart){
        float total = 0;
        for(Map.Entry entry: cart.getCart().entrySet()){
            ProductDTO productBuy =(ProductDTO) entry.getValue();
            total += productBuy.getQuantity() * productBuy.getPrice();
        }
        return total;
    }

    private String randomOrderID(){
        Random random = new Random();
        String orderID = "";
        while (true){
            orderID = "Ord" + random.nextInt(100000);
            OrderEntity entity = orderRepo.findByOrderID(orderID);
            if(entity==null) {
                break;
            }
        }
        return orderID;
    }
}
