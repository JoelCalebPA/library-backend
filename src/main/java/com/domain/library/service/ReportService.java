package com.domain.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.model.CartItem;
import com.domain.library.repository.BookRepository;
import com.domain.library.repository.CartItemRepository;

@Service
public class ReportService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private BookRepository bookRepository;

	public List<CartItem> listBooksByQuantity() {
		List<CartItem> report = new ArrayList<CartItem>();
		cartItemRepository.findAll().stream().collect(Collectors.groupingBy(item -> item.getBook().getId(),
				Collectors.summingInt(item -> item.getQuantity()))).forEach((book, quantity) -> {
					CartItem ci = new CartItem();
					ci.setBook(bookRepository.findById(book).get());
					ci.setQuantity(quantity);
					report.add(ci);
				});
		return report;
	}

}
