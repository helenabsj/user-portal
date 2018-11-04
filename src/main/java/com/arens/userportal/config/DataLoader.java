package com.arens.userportal.config;

import com.arens.userportal.entity.Address;
import com.arens.userportal.entity.User;
import com.arens.userportal.repository.AddressRepository;
import com.arens.userportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader  implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public DataLoader(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        {
            User user = new User("john", "doe", "john@arens.com", "703-869-1311", "tennis");
            Address address = new Address("20977 Fowlers Mill Circle", "Ashburn", "Virginia", "20147");

            user.setAddress(address);

            addressRepository.save(address);
            userRepository.save(user);
        }
        {
            User user = new User("joe", "doe", "joe@arens.com", "703-111-1111", "soccer");
            Address address = new Address("1446 Mount Vernon", "Williamsburg", "Virginia", "20147");

            user.setAddress(address);

            addressRepository.save(address);
            userRepository.save(user);
        }
    }
}
