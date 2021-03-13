package com.punjab.elasticsearch.autocomplete.service;

import com.punjab.elasticsearch.autocomplete.model.User;
import com.punjab.elasticsearch.autocomplete.repository.UserRepository;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
        return this.userRepository.findAll();
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public long count() {
        return this.userRepository.count();
    }

    public List<User> search(String keywords) {
        new DeleteRequest("person", id);
        MatchQueryBuilder searchByCountries = QueryBuilders.matchQuery("country", keywords);
        return this.userRepository.search(searchByCountries);
    }
}
