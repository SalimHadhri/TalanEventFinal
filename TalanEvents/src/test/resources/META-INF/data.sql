drop table if exists Event;

CREATE TABLE Event (eventId VARCHAR(250) PRIMARY KEY,
nameEvent VARCHAR(250),
descriptionEvent VARCHAR(250),
lieuEvent VARCHAR(250),
dateDebutEvent DATE(250),
dateFinEvent DATE(250),
nombreParticipantsEvent INTEGER(250),
annulée BOOLEAN FALSE,
pathImage VARCHAR(250),
status VARCHAR "validée",
);

INSERT INTO Event(eventId,nameEvent,descriptionEvent,lieuEvent,dateDebutEvent,dateFinEvent,nombreParticipantsEvent,annulée,pathImage,status) VALUES

                  (1,"monEvent","event fin d'annee","tunis","01/01/2022","01/05/2022",50,FALSE,null,"validé"),
                   (2,"monEvent2","event fin d'annee2","tunis2","01/01/2022","01/05/2022",25,FALSE,null,"validé"),
                    (3,"monEvent3","event fin d'annee3","tunis3","01/01/2022","01/05/2022",400,FALSE,null,"validé");