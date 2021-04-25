AdminRestController
get All Admins
curl -s http://localhost:8080/vote_restaurants/rest/admins

get Admins 100000
curl -s http://localhost:8080/vote_restaurants/rest/admins/100000

DishRestController
get All Dishes For Admin 100000
curl -s http://localhost:8080/vote_restaurants/rest/admins/dishes/100000

create Dishes For Admin 100000 (!!! HTTP Status 400 – Bad Request)
curl -s -X POST -d '{"name":"новая еда","price":"250"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/vote_restaurants/rest/admins/dishes/create --user admin0@yandex.ru:password0

delete Dishes 100007 For Admin 100000
curl -s -X DELETE http://localhost:8080/vote_restaurants/rest/admins/dishes/delete/100007 --user admin0@yandex.ru:password0

UserRestController
delete Auth Users (!!! HTTP Status 500 – Internal Server Error)
curl -s -X DELETE http://localhost:8080/vote_restaurants/rest/users/profile/delete --user user0@yandex.ru:password0

register Users (!!! HTTP Status 500 – Internal Server Error)
curl -s -i -X POST -d '{"name":"New User","email":"testuser@mail.ru","password":"testpassword"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/vote_restaurants/rest/users/register

update User 100002 (!!! HTTP Status 415 – Unsupported Media Type)
curl -s -X PUT -d '{"name":"Update User","email":"updateuser@mail.ru","password":"updatepassword"}' -H 'Content-Type: application/json' http://localhost:8080/vote_restaurants/rest/users --user user0@yandex.ru:password0

VoteRestController
get All Vote
curl -s http://localhost:8080/vote_restaurants/rest/admins/votes

get All Vote For Admin 100000
curl -s http://localhost:8080/vote_restaurants/rest/admins/votes/100000

create Vote User 100002 Admin 100000 (!!! HTTP Status 500 – Internal Server Error)
curl -s -X POST -d '{"localDate":"2020-09-20","localTime":"10:00:00","user_id":100002}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/vote_restaurants/rest/admins/votes/100000 --user user0@yandex.ru:password0