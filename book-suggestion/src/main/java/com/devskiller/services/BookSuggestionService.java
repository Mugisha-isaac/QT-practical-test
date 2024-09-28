package com.devskiller.services;

import com.devskiller.model.Author;
import com.devskiller.model.Book;
import com.devskiller.model.Reader;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service responsible for suggesting books to readers.
 * Author: Isaac MUGISHA
 */

class BookSuggestionService {

	private final Set<Book> books;
	private final Set<Reader> readers;

	public BookSuggestionService(Set<Book> books, Set<Reader> readers) {
		this.books = books;
		this.readers = readers;
	}

	Set<String> suggestBooks(Reader reader) {
		return books.stream()
				.filter(book -> book.rating() >= 4)
				.filter(book -> reader.favouriteGenres().contains(book.genre()))
				.filter(book -> readers.stream()
						.filter(r -> r.age() == reader.age())
						.filter(r -> !r.equals(reader))
						.anyMatch(r -> r.favouriteBooks().contains(book)))
				.map(Book::title)
				.collect(Collectors.toSet());

	}

	Set<String> suggestBooks(Reader reader, int rating) {

		return books.stream()
				.filter(book -> book.rating() == rating)
				.filter(book -> reader.favouriteGenres().contains(book.genre()))
				.filter(book -> readers.stream()
						.filter(r -> r.age() == reader.age())
						.filter(r -> !r.equals(reader))
						.anyMatch(r -> r.favouriteBooks().contains(book)))
				.map(Book::title)
				.collect(Collectors.toSet());
	}

	Set<String> suggestBooks(Reader reader, Author author) {
		return books.stream()
				.filter(book -> book.rating() >= 4)
				.filter(book -> reader.favouriteGenres().contains(book.genre()))
				.filter(book -> readers.stream()
						.filter(r -> r.age() == reader.age())
						.filter(r -> !r.equals(reader))
						.anyMatch(r -> r.favouriteBooks().contains(book)))
				.filter(book -> book.author().equals(author))
				.map(Book::title)
				.collect(Collectors.toSet());
	}

}
