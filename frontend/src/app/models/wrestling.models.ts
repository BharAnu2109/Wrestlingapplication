export enum WeightCategory {
  MEN_57KG = 'MEN_57KG',
  MEN_61KG = 'MEN_61KG',
  MEN_65KG = 'MEN_65KG',
  MEN_70KG = 'MEN_70KG',
  MEN_74KG = 'MEN_74KG',
  MEN_79KG = 'MEN_79KG',
  MEN_86KG = 'MEN_86KG',
  MEN_92KG = 'MEN_92KG',
  MEN_97KG = 'MEN_97KG',
  MEN_125KG = 'MEN_125KG',
  WOMEN_50KG = 'WOMEN_50KG',
  WOMEN_53KG = 'WOMEN_53KG',
  WOMEN_55KG = 'WOMEN_55KG',
  WOMEN_57KG = 'WOMEN_57KG',
  WOMEN_59KG = 'WOMEN_59KG',
  WOMEN_62KG = 'WOMEN_62KG',
  WOMEN_65KG = 'WOMEN_65KG',
  WOMEN_68KG = 'WOMEN_68KG',
  WOMEN_72KG = 'WOMEN_72KG',
  WOMEN_76KG = 'WOMEN_76KG'
}

export enum WrestlingStyle {
  FREESTYLE = 'FREESTYLE',
  GRECO_ROMAN = 'GRECO_ROMAN',
  FOLKSTYLE = 'FOLKSTYLE'
}

export enum MatchStatus {
  SCHEDULED = 'SCHEDULED',
  IN_PROGRESS = 'IN_PROGRESS',
  COMPLETED = 'COMPLETED',
  CANCELLED = 'CANCELLED',
  POSTPONED = 'POSTPONED'
}

export enum MatchResult {
  WIN_BY_FALL = 'WIN_BY_FALL',
  WIN_BY_TECHNICAL_FALL = 'WIN_BY_TECHNICAL_FALL',
  WIN_BY_DECISION = 'WIN_BY_DECISION',
  WIN_BY_DISQUALIFICATION = 'WIN_BY_DISQUALIFICATION',
  WIN_BY_FORFEIT = 'WIN_BY_FORFEIT',
  DRAW = 'DRAW',
  NO_CONTEST = 'NO_CONTEST'
}

export interface Wrestler {
  id?: number;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  weight: number;
  weightCategory: WeightCategory;
  nationality: string;
  club?: string;
  coach?: string;
  wins?: number;
  losses?: number;
  draws?: number;
  totalPoints?: number;
  ranking?: number;
  createdAt?: string;
  updatedAt?: string;
}

export interface Match {
  id?: number;
  wrestler1: Wrestler;
  wrestler2: Wrestler;
  tournament?: Tournament;
  matchDate: string;
  status: MatchStatus;
  result?: MatchResult;
  winner?: Wrestler;
  wrestler1Score?: number;
  wrestler2Score?: number;
  duration?: number;
  style: WrestlingStyle;
  venue?: string;
  referee?: string;
  notes?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface Tournament {
  id?: number;
  name: string;
  description?: string;
  startDate: string;
  endDate: string;
  venue?: string;
  city?: string;
  country?: string;
  status: string;
  style: WrestlingStyle;
  maxParticipants?: number;
  entryFee?: number;
  prizeMoney?: number;
  organizer?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface ApiResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}