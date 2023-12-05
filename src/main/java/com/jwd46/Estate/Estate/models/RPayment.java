package com.jwd46.Estate.Estate.models;

import jakarta.persistence.*;
import lombok.Data;
    @Entity
    @Table(name = "Rpayments")
    @Data
    public class RPayment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int userId;
        private int homeId;
        private String homeNo;
        private String userName;
        private String amount;
        private String startdate;
        private String enddate;
        private String payment;
    }
