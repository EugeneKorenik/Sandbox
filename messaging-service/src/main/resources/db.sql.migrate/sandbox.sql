

/** 2873dbdf-253e-47fb-bc63-e5dfabb37d5d */

select d.* from dialogue d
join account_dialogue ad
on d.id = ad.dialogue_id
where ad.account_id = '2873dbdf-253e-47fb-bc63-e5dfabb37d5d';