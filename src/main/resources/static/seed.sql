INSERT INTO blood_types_donation (type) VALUES
                                            (0), -- A+
                                            (1), -- A-
                                            (2), -- B+
                                            (3), -- B-
                                            (4), -- AB+
                                            (5), -- AB-
                                            (6), -- O+
                                            (7); -- O-


INSERT INTO blood_types_compatibility (donor_blood_type_id, recipient_blood_type_id) VALUES
                                                                                         (1, 5), (1, 1);

INSERT INTO blood_types_compatibility (donor_blood_type_id, recipient_blood_type_id) VALUES
                                                                                         (2, 1), (2, 2), (2, 5), (2, 6);

INSERT INTO blood_types_compatibility (donor_blood_type_id, recipient_blood_type_id) VALUES
                                                                                         (3, 3), (3, 5);

INSERT INTO blood_types_compatibility (donor_blood_type_id, recipient_blood_type_id) VALUES
                                                                                         (4, 3), (4, 4), (4, 5), (4, 6);

INSERT INTO blood_types_compatibility (donor_blood_type_id, recipient_blood_type_id) VALUES
    (5, 5);

INSERT INTO blood_types_compatibility (donor_blood_type_id, recipient_blood_type_id) VALUES
                                                                                         (6, 5), (6, 6);

INSERT INTO blood_types_compatibility (donor_blood_type_id, recipient_blood_type_id) VALUES
                                                                                         (7, 1), (7, 3), (7, 5), (7, 7);

INSERT INTO blood_types_compatibility (donor_blood_type_id, recipient_blood_type_id) VALUES
                                                                                         (8, 1), (8, 2), (8, 3), (8, 4), (8, 5), (8, 6), (8, 7), (8, 8);