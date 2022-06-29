create or replace procedure create_test_dialogue() AS $$
    declare
        err_context text;

        first_account_id   uuid := '2fa4a470-b2b2-11ec-b909-0242ac120002';
        second_account_id  uuid := '2fa4a862-b2b2-11ec-b909-0242ac120002';
        third_account_id   uuid := '2fa4ab1e-b2b2-11ec-b909-0242ac120002';
        fourth_account_id  uuid := '2fa4ad9e-b2b2-11ec-b909-0242ac120002';
        fifth_account_id   uuid := '2fa4b3a2-b2b2-11ec-b909-0242ac120002';

        first_dialogue_id  uuid := '2fa4b578-b2b2-11ec-b909-0242ac120002';
        second_dialogue_id uuid := '2fa4b7f8-b2b2-11ec-b909-0242ac120002';
        third_dialogue_id  uuid := '2fa4ba5a-b2b2-11ec-b909-0242ac120002';

    begin
        insert into account("id", "login", "password") values (first_account_id, 'first', 'first');
        insert into account("id", "login", "password") values (second_account_id, 'second', 'second');
        insert into account("id", "login", "password") values (third_account_id, 'third', 'third');
        insert into account("id", "login", "password") values (fourth_account_id, 'fourth', 'fourth');
        insert into account("id", "login", "password") values (fifth_account_id, 'fifth', 'fifth');

        insert into dialogue("id", "name") values (first_dialogue_id, 'first dialogue');
        insert into dialogue("id", "name") values (second_dialogue_id, 'second dialogue');
        insert into dialogue("id", "name") values (third_dialogue_id, 'group dialogue');

        insert into account_dialogue("dialogue_id", "account_id") values (first_dialogue_id, first_account_id);
        insert into account_dialogue("dialogue_id", "account_id") values (first_dialogue_id, second_account_id);

        insert into account_dialogue("dialogue_id", "account_id") values (second_dialogue_id, third_account_id);
        insert into account_dialogue("dialogue_id", "account_id") values (second_dialogue_id, fourth_account_id);

        insert into account_dialogue("dialogue_id", "account_id") values (third_dialogue_id, first_account_id);
        insert into account_dialogue("dialogue_id", "account_id") values (third_dialogue_id, second_account_id);
        insert into account_dialogue("dialogue_id", "account_id") values (third_dialogue_id, third_account_id);

        raise notice 'Test dialogue have been added successfully.';

    exception when others then
        GET STACKED DIAGNOSTICS err_context = PG_EXCEPTION_CONTEXT;
        RAISE WARNING 'Error Name:%',SQLERRM;
        RAISE WARNING 'Error State:%', SQLSTATE;
        RAISE WARNING 'Error Context:%', err_context;

    end
$$ language plpgsql;

call create_test_dialogue();