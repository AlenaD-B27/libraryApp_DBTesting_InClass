select count(*) from users;
select count(*) from books;


select count(*) from book_borrow
where is_returned = 0;

select name from book_categories;

select * from books
where name = 'Agile Testing';

select full_name from users
where email = 'librarian55@library';

select status from users
where email='librarian10@library';

select count(*) from users
where status = 'INACTIVE';
