<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('estimation_request_picture', function (Blueprint $table) {
            $table->id()->comment('고유키');
            $table->unsignedBigInteger('estimation_request_id')->index()->comment('고객 견적 의뢰 고유키');
            $table->dateTime('created_at')->index()->comment('생성일시');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('estimation_request_picture');
    }
};
