export interface Order {
  clientName: string;
  phoneNumber: string;
  orderTotal: number;
  masterId: number;
  servicesId: number[];
  comment: string;
  errors?: string[];
}
